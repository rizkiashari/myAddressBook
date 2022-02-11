package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Registered {

    @SerializedName("date")
    private String date;

    @SerializedName("age")
    private String age;

    public String getDate(){
        return date;
    }

    public String getAge(){
        return age;
    }
}
