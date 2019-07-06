package com.bawei.yangshi2019073;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.yangshi2019073.adapter.PageAdapter;
import com.bawei.yangshi2019073.base.BaseActivity;
import com.bawei.yangshi2019073.fragment.Fragment1;
import com.bawei.yangshi2019073.fragment.Fragment2;
import com.bawei.yangshi2019073.fragment.Fragment3;
import com.bawei.yangshi2019073.fragment.Fragment4;
import com.bawei.yangshi2019073.fragment.Fragment5;
import com.bawei.yangshi2019073.utils.HttpUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ImageView iv;
    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment>list=new ArrayList<>();

    //写继承主页面的布局
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    //写获取id的
    @Override
    public void initFind() {
     iv=findViewById(R.id.iv);
     tab=findViewById(R.id.tab);
     vp=findViewById(R.id.vp);
    }

    //写数据的
    @Override
    public void initData() {
        if (!HttpUtils.isNetWork(MainActivity.this)){
            Toast.makeText(this, "没网了", Toast.LENGTH_SHORT).show();
        }
     list.add(new Fragment1());
     list.add(new Fragment2());
     list.add(new Fragment3());
     list.add(new Fragment4());
     list.add(new Fragment5());
     PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),list);
     vp.setAdapter(pageAdapter);
        for (int i = 0; i < list.size(); i++) {
            tab.addTab(tab.newTab());
        }
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("风景");
        tab.getTabAt(1).setText("美女");
        tab.getTabAt(2).setText("动漫卡通");
        tab.getTabAt(3).setText("娱乐明星");
        tab.getTabAt(4).setText("萌娃");
    }

    //写监听事件的
    @Override
    public void initLinear() {
      iv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_PICK);
              intent.setType("image/*");
              startActivityForResult(intent,1);

          }
      });
    }

    //回调图片的方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&data!=null){
            Uri data1 = data.getData();
            Glide.with(MainActivity.this).load(data1).apply(new RequestOptions().circleCrop()).into(iv);
        }
    }
}
