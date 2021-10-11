package com.foodmp.controller;

public class ApiUtils {

    public static final String BASE_URL = "http://10.0.2.2:4000/api/";

   // public static final String DEVICE_BASE_URL = "http://192.168.6.6:4000/api/";

    public static UserServices getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserServices.class);
    }
}
