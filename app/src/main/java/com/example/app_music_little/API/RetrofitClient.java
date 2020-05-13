package com.example.app_music_little.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.Manifest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(1000, TimeUnit.MILLISECONDS).
                writeTimeout(1000, TimeUnit.MILLISECONDS).
                connectTimeout(1000, TimeUnit.MILLISECONDS).
                retryOnConnectionFailure(true)
                //thêm cái này vào để xem log (lúc request api với lại response trả về)
                .addNetworkInterceptor(httpLoggingInterceptor())
                //
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().
                baseUrl(base_url).
                client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
        return retrofit;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
