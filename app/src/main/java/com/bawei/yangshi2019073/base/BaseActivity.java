package com.bawei.yangshi2019073.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 08:54
 * 作用:抽基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义的四个方法
    public abstract int initLayout();
    public abstract void initFind();
    public abstract void initData();
    public abstract void initLinear();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initFind();
        initData();
        initLinear();
    }
}
