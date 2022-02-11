package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String medium;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getLarge(){
        return large;
    }

    public String getMedium(){
        return medium;
    }

    public String getThumbnail(){
        return thumbnail;
    }
}
