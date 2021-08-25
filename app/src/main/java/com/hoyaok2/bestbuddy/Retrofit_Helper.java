package com.hoyaok2.bestbuddy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit_Helper {
    static String baseUrl="http://tnswh9107.dothome.co.kr";
    Retrofit retrofit;

    static Retrofit getRetrofitInstanceGson(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(new NullOnEmptyConverterFactory());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit;
    }

    static Retrofit getRetrofitInstanceScalars(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;
    }
}