package com.example.app_music_little.API;

import  android.Manifest;
public class RetrofitServer {
    private final static String base_url="https://littlefeelings.000webhostapp.com/Server/";

    public static DataService getService(){
        return RetrofitClient
                .getClient(base_url)
                .create(DataService.class);
    }
}
