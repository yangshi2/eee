package com.bawei.yangshi2019073.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.yangshi2019073.R;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 09:10
 * 作用:第二个页面展示webview
 */
public class Fragment2 extends Fragment {
    private WebView web;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item2, null, false);
        web=inflate.findViewById(R.id.web);
        web.loadUrl("http://blog.zhaoliang5156.cn/zixunnew/724D6A55496A11726628.html");
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        return inflate;
    }
}
