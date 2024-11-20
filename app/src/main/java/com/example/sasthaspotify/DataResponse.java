package com.example.sasthaspotify;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    @SerializedName("data")
    private List<Result> data;
    public List<Result> getData() {
        return data;
    }
}