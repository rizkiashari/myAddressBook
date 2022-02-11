package com.rizkiashari.myaddressbook.Network;

import com.rizkiashari.myaddressbook.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("people")
    Call<ResponseModel> getEmplpyeeList(@Query("nim") String nim, @Query("nama") String nama);

    @GET("people/{id}")
    Call<ResponseModel> getDetailEmployee(@Path("id") int id, @Query("nim") String nim, @Query("nama") String nama);
}
