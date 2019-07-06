package com.bawei.yangshi2019073.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bawei.yangshi2019073.MyListView;
import com.bawei.yangshi2019073.R;
import com.bawei.yangshi2019073.adapter.MyAdapter;
import com.bawei.yangshi2019073.bean.BannerBean;
import com.bawei.yangshi2019073.bean.Bean;
import com.bawei.yangshi2019073.utils.HttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 09:10
 * 作用:第一个fragment展示轮播图和listview
 */
public class Fragment1 extends Fragment {
    private PullToRefreshScrollView pull;
    private Banner ban;
    private MyListView mlv;
    private HttpUtils utils;
    private String str="http://blog.zhaoliang5156.cn/zixunnew/fengjing";
    private String strl="http://blog.zhaoliang5156.cn/zixunnew/fengjing?page=1";
    private MyAdapter myAdapter;

    //子线程   轮播图数据
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String string  = (String) msg.obj;
            Gson gson = new Gson();
            BannerBean bannerBean = gson.fromJson(string, BannerBean.class);
            List<BannerBean.DataBean.TopnewsBean> topnews = bannerBean.getData().getTopnews();
            ban.setImages(topnews);
            ban.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                 BannerBean.DataBean.TopnewsBean topnewsBean= (BannerBean.DataBean.TopnewsBean) path;
                    Glide.with(context).load("http://blog.zhaoliang5156.cn/zixunnew/"+topnewsBean.getImageUrl()).into(imageView);
                }
            });
            ban.isAutoPlay(true);
            ban.setDelayTime(3000);
            ban.start();

        }
    };

    //listview 的展示
    Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String string  = (String) msg.obj;
            Gson gson = new Gson();
            Bean bean = gson.fromJson(string, Bean.class);
            List<Bean.DataBean.NewsBean> news = bean.getData().getNews();
            myAdapter = new MyAdapter(news,getActivity());
            mlv.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.item1, null, false);
        pull=inflate.findViewById(R.id.pull);
        ban=inflate.findViewById(R.id.ban);
        mlv=inflate.findViewById(R.id.mlv);
        getData();
        //上拉下拉展示
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
             myAdapter=null;
             getData();
            }
        });
        return inflate;
    }

    private void getData() {
        utils = HttpUtils.getInstance();
        //子线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                String string = utils.getString(str);
                Message message = Message.obtain();
                message.obj=string;
                handler.sendMessage(message);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                String string = utils.getString(strl);
                Message message = Message.obtain();
                message.obj=string;
                handler1.sendMessage(message);
            }
        }.start();
    }
}
