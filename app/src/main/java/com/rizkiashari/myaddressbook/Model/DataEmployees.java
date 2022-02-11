package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class DataEmployees {

    @SerializedName("employeeId")
    private Integer employeeId;

    @SerializedName("gender")
    private String gender;

    @SerializedName("name")
    private Name name;

    @SerializedName("location")
    private Location location;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private Login login;

    @SerializedName("dob")
    private DOB dob;

    @SerializedName("registered")
    private Registered registered;

    @SerializedName("phone")
    private String phone;

    @SerializedName("cell")
    private String cell;

    @SerializedName("id")
    private Id id;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("nat")
    private String nat;

    public Integer getEmployeeId(){
        return employeeId;
    }

    public String getGender(){
        return gender;
    }

    public Location getLocation(){
        return location;
    }

    public String getEmail(){
        return email;
    }

    public Login getLogin(){
        return login;
    }

    public DOB getDob(){
        return dob;
    }

    public Registered getRegistered(){
        return registered;
    }

    public String getPhone(){
        return phone;
    }

    public String getCell(){
        return cell;
    }

    public Id getId(){
        return id;
    }

    public Picture getPicture(){
        return picture;
    }

    public String getNat(){
        return nat;
    }
}
