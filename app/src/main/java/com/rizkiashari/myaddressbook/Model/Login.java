package com.rizkiashari.myaddressbook.Model;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("salt")
    private String salt;

    @SerializedName("md5")
    private String md5;

    @SerializedName("shal")
    private String shal;

    @SerializedName("sha256")
    private String sha256;

    public String getUuid(){
        return uuid;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getSalt(){
        return salt;
    }

    public String getMd5(){
        return md5;
    }

    public String getShal(){
        return shal;
    }

    public String getSha256(){
        return sha256;
    }
}
