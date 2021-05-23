package com.example.bahasamata.remote;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://bahasamata.herokuapp.com/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
