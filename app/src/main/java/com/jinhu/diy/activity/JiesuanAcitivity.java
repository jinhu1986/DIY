package com.jinhu.diy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jinhu.diy.R;

public class JiesuanAcitivity extends AppCompatActivity {

    private TextView tv_jiesuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiesuan_acitivity);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");

        tv_jiesuan.setText(text);
    }

    private void initView() {
        tv_jiesuan = (TextView) findViewById(R.id.tv_jiesuan);
    }
}
