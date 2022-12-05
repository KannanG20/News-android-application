package com.example.infotech.Interfaces;

import com.example.infotech.ModelClass.ModelRV;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {
    @GET("v2/top-headlines?category=technology&language=en&apiKey=f7b3a441dc7b436087bdc6fbf20946b2")
    Call<ModelRV> getAllNews();
}
