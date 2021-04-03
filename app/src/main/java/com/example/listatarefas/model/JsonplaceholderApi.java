package com.example.listatarefas.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonplaceholderApi {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("albums")
    Call<List<Albums>> getAlbums();

    @GET("todos")
    Call<List<Todos>> getTodos();

    @GET("comments")
    Call<List<Comments>> getAllComments();

}
