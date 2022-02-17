package com.firebase.appesp32example1.fragment.api;

import com.firebase.appesp32example1.fragment.model.Example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    //link Api : https://api.openweathermap.org/data/2.5/weather?q=Hanoi&lang=vi&units=metric&appid=da366a22304bdc73cba13d51853d4ece

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-đ HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("https://api.openweathermap.org/data/2.5/weather?q=Hanoi&lang=vi&units=metric&appid=da366a22304bdc73cba13d51853d4ece")
    Call<Example> getDataWeatherFromApi();
}
