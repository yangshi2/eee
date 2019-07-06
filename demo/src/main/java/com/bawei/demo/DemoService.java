package com.bawei.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/4
 * Time: 10:09
 */
public class DemoService extends Service {
    public static final String DOWNLOAD_PATH=
            Environment.getExternalStorageDirectory().getAbsolutePath()+
                    "/downloads/";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_START.equals(intent.getAction())){
            FileInfo fileInfo= (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.i("test","Start"+fileInfo.toString());
        }else if (ACTION_STOP.equals(intent.getAction())){
            FileInfo fileInfo= (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.i("test","Stop"+fileInfo.toString());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }
    /**
     * 初始化子线程
     */
    class InitThread extends Thread{
        private FileInfo mFileInfo=null;

        public InitThread(FileInfo mFileInfo) {
            this.mFileInfo = mFileInfo;
        }
        public void run(){
            HttpURLConnection conn=null;
            try {
               //连接网络文件
                URL url = new URL(mFileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length=-1;
                if (conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                   //获得文件长度
                    length=conn.getContentLength();
                }
                if (length<=0){
                    return;
                }
                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()){
                    dir.mkdir();
                }
                //在本地创建文件
                File file = new File(dir,mFileInfo.getFilename());
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //设置文件长度
                raf.setLength(length);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
