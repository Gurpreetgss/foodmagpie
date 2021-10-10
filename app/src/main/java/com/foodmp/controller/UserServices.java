package com.foodmp.controller;

import com.foodmp.model.DataOrg;
import com.foodmp.model.LoginResponse;
import com.foodmp.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServices {
    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> loginUser(@Field("email") String email,
                                  @Field("password") String password);
    @POST("regOrg/")
    @FormUrlEncoded
    Call<Register> insertOrganzation(@Field("Org_id")int userid,@Field("Org_name") String name, @Field("Org_address") String address,
                                @Field("org_description") String desc, @Field("Org_phn") String phn,@Field("Org_lat") double lat,@Field("Org_long")double lang);

    @POST("reset/")
    @FormUrlEncoded
    Call<Register> resetPassword(@Field("Email") String email,
                                 @Field("password") String password);
    @POST("Signup/")
    @FormUrlEncoded
    Call<Register> userRegister(@Field("Name") String name, @Field("Email") String email,
                                @Field("Phone_num") String Contact, @Field("password") String password, @Field("User_type") String type);

    @GET("getOrg/")
    Call<DataOrg> getOrgList();
}
