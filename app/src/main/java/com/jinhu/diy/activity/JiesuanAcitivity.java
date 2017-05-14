package com.jinhu.diy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jinhu.diy.R;
import com.jinhu.diy.adapter.JiesuanAdapter;
import com.jinhu.diy.bean.ShopBean;

import java.util.List;

public class JiesuanAcitivity extends AppCompatActivity {

    private TextView tv_jiesuan;
    private RecyclerView ry_jiesuan;

    private JiesuanAdapter mAdapter;
    private List<ShopBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiesuan_acitivity);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mList = intent.getParcelableArrayListExtra("list");

        initRV(mList);
    }

    private void initRV(List<ShopBean.DataBean> list) {
        if (null != list) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            ry_jiesuan.setLayoutManager(manager);
            mAdapter = new JiesuanAdapter(list, JiesuanAcitivity.this);
            ry_jiesuan.setAdapter(mAdapter);
        }
    }

    private void initView() {
        ry_jiesuan = (RecyclerView) findViewById(R.id.ry_jiesuan);
    }
}
