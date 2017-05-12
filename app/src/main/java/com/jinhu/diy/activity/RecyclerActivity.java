package com.jinhu.diy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jinhu.diy.R;
import com.jinhu.diy.util.Url;
import com.jinhu.diy.adapter.RvAdapter;
import com.jinhu.diy.bean.GetBean;
import com.jinhu.diy.util.GsonUtils;
import com.jinhu.diy.util.OkUtils;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<GetBean.ResultBean.DataBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
        initData();

    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        RvAdapter adapter = new RvAdapter(mData);
        recycler_view.setAdapter(adapter);
    }

    private void initData() {

        OkUtils.getEnqueue(Url.NODE, null, new OkUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {
                GetBean getBean = GsonUtils.gsonToBean(result, GetBean.class);
                mData = getBean.getResult().getData();
                initRecycler();
            }

            @Override
            public void onError(String errorMsg) {
                Log.d("00000000000", "onError: --------------" + errorMsg);
            }
        });
    }

    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
    }
}
