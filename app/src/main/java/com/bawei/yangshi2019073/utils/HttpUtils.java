package com.bawei.yangshi2019073.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 09:19
 * 作用:工具类
 */
public class HttpUtils {
    //单例模式
    private static HttpUtils utils=new HttpUtils();
    private HttpUtils(){};
    public static HttpUtils getInstance(){
        return utils;
    }
    //封装数据
    public String getString(String strUrl){
        HttpURLConnection connection=null;
        try {
            //把string转换成url
            URL url = new URL(strUrl);
            //打开url
            connection = (HttpURLConnection) url.openConnection();
            //获取请求方式
            connection.setRequestMethod("GET");
            //设置超时时间
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //设置请求码
            int code = connection.getResponseCode();
            //判断请求码
            if (code==200){
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String str="";
                while((str=reader.readLine())!=null){
                    buffer.append(str);
                }
                stream.close();
                connection.disconnect();
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public void Task(String strUrl, final Back back){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return getString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                back.getData(s);
            }
        }.execute(strUrl);
    }
    public interface Back{
        void getData(String s);
    }
    //封装网络
    public Bitmap getbitmap(String strUrl) {
        HttpURLConnection connection = null;
        Bitmap bitmap=null;
        try {
            //把string转换成url
            URL url = new URL(strUrl);
            //打开url
            connection = (HttpURLConnection) url.openConnection();
            //获取请求方式
            connection.setRequestMethod("GET");
            //设置超时时间
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //设置请求码
            int code = connection.getResponseCode();
            //判断请求码
            if (code == 200) {
                InputStream stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public void getTask(String strUrl, final BackB backB){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                return getbitmap(strings[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                backB.getDataB(bitmap);
            }
        }.execute(strUrl);
    }
    public interface  BackB{
        void getDataB(Bitmap bitmap);
    }
    //判断网络
    public static boolean isNetWork(Context context){
        if (context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
