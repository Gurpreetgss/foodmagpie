package com.foodmp.controller;

import com.foodmp.model.LoginResponse;
import com.foodmp.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserServices {
    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> loginUser(@Field("email") String email,
                                  @Field("password") String password);
    @POST("Signup/")
    @FormUrlEncoded
    Call<Register> userRegister(@Field("Name") String name, @Field("Email") String email,
                                @Field("Phone_num") String Contact, @Field("password") String password, @Field("User_type") String type);

    @POST("reset/")
    @FormUrlEncoded
    Call<Register> resetPassword(@Field("Email") String email,
                                 @Field("password") String password);
}
