package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview.APIs;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUrl {

    private static final String BASE_URL = "https://squareandcube.000webhostapp.com/AptitudePreparation/public/";
    private static APIUrl mInstance;
    private static Retrofit retrofit=null;

    private OkHttpClient getRequestHeader() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }
    private  APIUrl()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized APIUrl getmInstance()
    {
        if(mInstance == null)
        {
            mInstance =new APIUrl();
        }
        return mInstance;
    }
    public ApiService getApi()
    {
        return retrofit.create(ApiService.class);
    }
}
