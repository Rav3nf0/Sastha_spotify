package com.example.sasthaspotify;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
public interface APIs {
    static String BASE_URL = "https://deezerdevs-deezer.p.rapidapi.com/";
    @Headers({
            "X-RapidAPI-Key: 5ade01f002mshba114dc3b247908p16fe31jsn63f5d0741f78",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    })
    @GET("search?q=eminem")
    Call<DataResponse> getSongs();
}
