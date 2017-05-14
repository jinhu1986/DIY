package com.jinhu.diy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jinhu.diy.R;
import com.jinhu.diy.adapter.ShopAdapter;
import com.jinhu.diy.bean.ShopBean;
import com.jinhu.diy.util.GsonUtils;
import com.jinhu.diy.util.OkUtils;
import com.jinhu.diy.util.Url;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox check_quanxuan;
    private RecyclerView recy_check;
    private Button btn_check;
    private List<ShopBean.DataBean> mData;
    private ShopAdapter mAdapter;
    private CheckBox check_fanxuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_acticity);
        initView();
        getServerData();
    }

    private void getServerData() {
        OkUtils.getEnqueue(Url.SHOPING, null, new OkUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {
                ShopBean shopBean = GsonUtils.gsonToBean(result, ShopBean.class);
                mData = shopBean.getData();
                initRecyclerView(mData);
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(ShopActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView(List<ShopBean.DataBean> data) {
        if (null != data) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recy_check.setLayoutManager(manager);
            mAdapter = new ShopAdapter(data, ShopActivity.this);
            recy_check.setAdapter(mAdapter);
        }
    }

    private void initView() {
        check_quanxuan = (CheckBox) findViewById(R.id.check_quanxuan);
        check_quanxuan.setOnClickListener(this);
        recy_check = (RecyclerView) findViewById(R.id.recy_check);
        btn_check = (Button) findViewById(R.id.btn_check);

        btn_check.setOnClickListener(this);
        check_fanxuan = (CheckBox) findViewById(R.id.check_fanxuan);
        check_fanxuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                ArrayList<ShopBean.DataBean> list = new ArrayList<>();
                Intent intent = new Intent(ShopActivity.this, JiesuanAcitivity.class);
                if (null != mData) {
                    for (int i = 0; i < mData.size(); i++) {
                        if (mData.get(i).isIs_allow_credit()) {
                            list.add(mData.get(i));
                        }
                    }
                }
                intent.putParcelableArrayListExtra("list", list);
                startActivity(intent);
                break;
            case R.id.check_quanxuan:
                boolean flag = check_quanxuan.isChecked();
                mAdapter.allBoxChecked(flag);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.check_fanxuan:
                mAdapter.checkBoxFanXuan();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
