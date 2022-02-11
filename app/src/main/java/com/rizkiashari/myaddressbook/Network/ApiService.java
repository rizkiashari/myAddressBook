package com.rizkiashari.myaddressbook.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASEURL = "https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/stage2/";
    private static HttpLoggingInterceptor loggingInterceptor = null;
    private static Retrofit retrofit = null;
    private static OkHttpClient client = null;

    public static ApiCall apiCall(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).client(client).build();

        return retrofit.create(ApiCall.class);
    }
}
