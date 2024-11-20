package com.example.sasthaspotify;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static ApiClient instance = null;
    private APIs apis;
    private ApiClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIs.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apis = retrofit.create(APIs.class);
    }
    public static synchronized ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }
    public APIs getApis(){
        return apis;
    }
}
