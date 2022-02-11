package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseModel {

    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama")
    private String nama;

    @SerializedName("credits")
    private String credits;

    @SerializedName("employeeId")
    private Object employeeId;

    @SerializedName("employees")
    private List<DataEmployees> employees;

    public Integer getStatusCode(){
        return statusCode;
    }

    public String getNim(){
        return nim;
    }

    public String getNama(){
        return nama;
    }

    public String getCredits(){
        return credits;
    }

    public Object getEmployeeId(){
        return employeeId;
    }

    public List<DataEmployees> getEmployees(){
        return employees;
    }
}
