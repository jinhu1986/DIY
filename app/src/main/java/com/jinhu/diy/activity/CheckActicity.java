package com.jinhu.diy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.jinhu.diy.R;
import com.jinhu.diy.adapter.CheckAdapter;
import com.jinhu.diy.bean.CheckBean;

import java.util.ArrayList;
import java.util.List;

public class CheckActicity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recy_check;
    private List<CheckBean> mList;
    private CheckBox check_quanxuan;
    private Button btn_check;
    private CheckAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_acticity);
        initView();
        initData();
        initRecy();
    }

    private void initRecy() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy_check.setLayoutManager(linearLayoutManager);
        mAdapter = new CheckAdapter(mList);
        recy_check.setAdapter(mAdapter);
        mAdapter.getOnClickListener(new CheckAdapter.OnClickListener() {
            @Override
            public void setOnClickListener(boolean flag) {
                //check_quanxuan.setChecked(flag);
            }
        });
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CheckBean checkBean = new CheckBean();
            checkBean.id = i;
            checkBean.name = "第" + i + "个";
            checkBean.setCheck(false);
            mList.add(checkBean);
        }
    }

    private void initView() {
        recy_check = (RecyclerView) findViewById(R.id.recy_check);
        check_quanxuan = (CheckBox) findViewById(R.id.check_quanxuan);
        check_quanxuan.setOnClickListener(this);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                StringBuilder builder = new StringBuilder();
                Intent intent = new Intent(CheckActicity.this, JiesuanAcitivity.class);
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isCheck()) {
                        builder.append(mList.get(i).getName());
                        Log.d("1111111111", "onClick: ---------" + builder.toString());
                    }
                }
                intent.putExtra("text", builder.toString());
                startActivity(intent);
                break;
            case R.id.check_quanxuan:
                boolean b = ((CheckBox) check_quanxuan).isChecked();
                mAdapter.checkBoxXunaZe(b);
                mAdapter.notifyDataSetChanged();
                break;

        }
    }
}
