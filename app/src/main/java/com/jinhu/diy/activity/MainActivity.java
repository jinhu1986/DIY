package com.jinhu.diy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhu.diy.R;
import com.jinhu.diy.util.Url;
import com.jinhu.diy.util.OkUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "       ";
    OkHttpClient mClient = new OkHttpClient();
    private Button btn_01;
    private Button btn_02;
    private Button btn_03;
    private Button btn_04;
    private String mTongbu;
    private Button btn_05;
    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Map<String, String> map = new HashMap<>();
        map.put(Url.KEY, Url.VALUE);
        OkUtils.getEnqueue(Url.ADD, map, new OkUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {
                text_view.setText(result);
            }

            @Override
            public void onError(String errorMsg) {
                Log.d(TAG, "onError: -----------" + errorMsg);
            }
        });
        btn_01.setOnClickListener(view -> Toast.makeText(this, "222", Toast.LENGTH_SHORT).show());
    }

    /**
     * get同步
     */
    public String getTongbu() {
        HttpUrl httpUrl = HttpUrl.parse(Url.NODE).newBuilder()
//                .addQueryParameter(Url.KEY, Url.VALUE)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
/*        RequestBody formBody = new FormBody.Builder()
                .add(Url.KEY, Url.VALUE)
                .build();

        Request request = new Request.Builder()
                .url(Url.ADD)
                .method("GET", formBody)
                .build();*/
        try {
            Response response = mClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get异步
     */
    public String getYibu() {
        HttpUrl httpUrl = HttpUrl.parse(Url.ADD).newBuilder()
                .addQueryParameter(Url.KEY, Url.VALUE)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.d("GET异步", "onResponse: ---------------" + response.body().string());
            }
        });
        return null;
    }

    /**
     * post同步
     */
    public String postTongbu() {
        FormBody formBody = new FormBody.Builder()
                .add(Url.KEY_01, Url.VALUE_01)
                .add(Url.KEY_02, Url.VALUE_02)
                .add(Url.KEY_03, Url.VALUE_03)
                .add(Url.KEY_04, Url.VALUE_04)
                .add(Url.KEY_05, Url.VALUE_05)
                .build();

        Request request = new Request.Builder()
                .url(Url.POST)
//                .method("GET",formBody)
                .post(formBody)
                .build();

        try {
            Response response = mClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post异步
     */
    public String postYibu() {
        FormBody formBody = new FormBody.Builder()
                .add(Url.KEY_01, Url.VALUE_01)
                .add(Url.KEY_02, Url.VALUE_02)
                .add(Url.KEY_03, Url.VALUE_03)
                .add(Url.KEY_04, Url.VALUE_04)
                .add(Url.KEY_05, Url.VALUE_05)
                .build();

        Request request = new Request.Builder()
                .url(Url.POST)
//                .method("GET",formBody)
                .post(formBody)
                .build();

        try {
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("POST异步", "onResponse: +++++++++++++++++" + response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initView() {
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
        btn_03 = (Button) findViewById(R.id.btn_03);
        btn_04 = (Button) findViewById(R.id.btn_04);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_05 = (Button) findViewById(R.id.btn_05);
        btn_05.setOnClickListener(this);
        text_view = (TextView) findViewById(R.id.text_view);
        text_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
/*            case R.id.btn_01:
                Map<String, String> map = new HashMap<>();
                map.put(Url.KEY, Url.VALUE);
                OkUtils.getExcute(Url.ADD, map, new OkUtils.MyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        text_view.setText(result);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.d(TAG, "onError: -----------" + errorMsg);
                    }
                });
                break;*/
            case R.id.btn_02:
                getYibu();
                break;
            case R.id.btn_03:
                new Thread(

                ).start();
                break;
            case R.id.btn_04:
                postYibu();
                break;
            case R.id.btn_05:
//                if (mTongbu != null) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
//                    intent.putExtra("json", mTongbu);
                startActivity(intent);
//                }
                break;
        }
    }
}
