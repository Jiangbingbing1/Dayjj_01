package com.bawei.dayjj_01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyOkhttp {

    private OkHttpClient okHttpClient;
    private static final MyOkhttp ourInstance = new MyOkhttp();

    public static MyOkhttp getInstance() {
        return ourInstance;
    }

    private MyOkhttp() {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor=loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();

    }
    public interface MyCallback{
        void success(String json);
        void error(String error);
    }
    private MyCallback myCallback;
    public void RequestJson(final MyCallback myCallback) throws IOException {
        this.myCallback=myCallback;
        Request request=new Request.Builder()
                .url("http://172.17.8.100/small/commodity/v1/bannerShow")
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                  myCallback.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myCallback.success(response.body().string());
            }
        });
    }



}
