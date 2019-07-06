package com.bawei.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv=null;
    private ProgressBar pb=null;
    private Button btstop=null;
    private Button btstart=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        pb=findViewById(R.id.pb);
        btstop=findViewById(R.id.btstop);
        btstart=findViewById(R.id.btstart);

        //創建文件信息对象
        final FileInfo fileInfo = new FileInfo(0,"https://dldir1.qq.com/music/clntupate/QQMusic_YQQProductNew.exe",
                "QQMusic_YQQProductNew.exe",0,0);
        //添加事件监听
        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Intent传递参数给Service
                Intent intent = new Intent(MainActivity.this, DemoService.class);
                intent.setAction(DemoService.ACTION_START);
                intent.putExtra("fileInfo",fileInfo);
                startService(intent);
            }
        });
        btstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Intent传递参数给Service
                Intent intent = new Intent(MainActivity.this, DemoService.class);
                intent.setAction(DemoService.ACTION_STOP);
                intent.putExtra("fileInfo",fileInfo);
                startService(intent);
            }
        });

    }
}
