package com.foodmp.controller;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        OkHttpClient builder = new OkHttpClient().
                newBuilder().connectTimeout(5, TimeUnit.MINUTES).
        writeTimeout(5, TimeUnit.MINUTES).
        readTimeout(5, TimeUnit.MINUTES).build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).client(builder)
                    .build();
        }
        return retrofit;
    }
}