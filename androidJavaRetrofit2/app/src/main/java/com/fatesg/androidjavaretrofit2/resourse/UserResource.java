package com.fatesg.androidjavaretrofit2.resourse;

import com.fatesg.androidjavaretrofit2.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserResource {

    @GET("/users")
    Call<List<User>> get();

    @POST("/users")
    Call<User> post(@Body User user);

    @PUT("/users")
    Call<User> put(@Body User user);

    @DELETE("/users")
    Call<User> delete(@Body User user);
}

