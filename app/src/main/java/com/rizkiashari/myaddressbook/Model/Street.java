package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Street {

    @SerializedName("number")
    private Integer number;

    @SerializedName("name")
    private String name;

    public Integer getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }
}
