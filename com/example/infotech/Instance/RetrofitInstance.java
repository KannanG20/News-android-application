package com.example.infotech.Instance;

import com.example.infotech.Interfaces.RetroInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static String base_url = "https://newsapi.org/";

    public static RetrofitInstance instance;
    public static RetroInterface retrofitApi;

    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retrofit.create(RetroInterface.class);
    }

    public static RetrofitInstance getInstance(){
        if(instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }
}
