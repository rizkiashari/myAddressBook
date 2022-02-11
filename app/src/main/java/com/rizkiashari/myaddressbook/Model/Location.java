package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    private Street street;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("postcode")
    private Integer postcode;

    @SerializedName("coordinates")
    private Coordinates coordinates;

    @SerializedName("timezone")
    private Timezone timezone;

    public Street getStreet(){
        return street;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getCountry(){
        return country;
    }

    public Integer getPostcode(){
        return postcode;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public Timezone getTimezone(){
        return timezone;
    }
}
