package com.example.bahasamata.remote;

import com.example.bahasamata.model.ModelLogin;
import com.example.bahasamata.model.ModelRegister;
import com.example.bahasamata.model.User;

//import okhttp3.String;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {

    @FormUrlEncoded
    @POST("register")
    Call<ModelRegister> register(@Field("name") String name, @Field("username") String username
            , @Field("password") String password, @Field("type") String type);
    @FormUrlEncoded
    @POST("login")
    Call<ModelLogin> login(@Field("username") String username, @Field("password") String password
            , @Field("type") String type);
}
