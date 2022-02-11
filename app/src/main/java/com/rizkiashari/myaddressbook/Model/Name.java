package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("title")
    private String title;

    @SerializedName("first")
    private String first;

    @SerializedName("last")
    private String last;

    public String getTitle(){
        return title;
    }

    public String getFirst(){
        return first;
    }

    public String getLast(){
        return last;
    }
}
