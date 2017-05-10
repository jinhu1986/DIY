package com.jinhu.diy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * get同步
     */
    public String getTongbu() {
        HttpUrl httpUrl = HttpUrl.parse(Url.ADD).newBuilder()
                .addQueryParameter(Url.KEY, Url.VALUE)
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                final String tongbu = getTongbu();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("GET同步", "run: 111111111111111111" + tongbu);
                                        Toast.makeText(MainActivity.this, tongbu, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                ).start();
                break;
            case R.id.btn_02:
                getYibu();
                break;
            case R.id.btn_03:
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                final String tongbu = postTongbu();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("POST同步", "run: 22222222222222222" + tongbu);
                                        Toast.makeText(MainActivity.this, tongbu, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                ).start();
                break;
            case R.id.btn_04:
                postYibu();
                break;
        }
    }
}
