package com.hoyaok2.bestbuddy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit_Helper {
    static String baseUrl="http://jhyein1122.dothome.co.kr";

    static Retrofit getRetrofitInstanceGson(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();

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
