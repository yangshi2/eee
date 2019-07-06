package com.bawei.yangshi2019073.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 10:10
 * 作用:fragment的抽基类
 */
public abstract class BaseFragment extends Fragment {
    public abstract int initLyaout();
    public abstract void initFind();
    public abstract void initData();
    public abstract void initLinear();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLyaout();
        initFind();
        initData();
        initLinear();
    }
}
