package com.example.sasthaspotify;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    long id;
    @SerializedName("title")
    String title;
    @SerializedName("preview")
    String previewUrl;

    @SerializedName("cover")
    String image;
    public String getPreviewUrl() {
        return previewUrl;
    }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage(){
        return  image;
    }

}
