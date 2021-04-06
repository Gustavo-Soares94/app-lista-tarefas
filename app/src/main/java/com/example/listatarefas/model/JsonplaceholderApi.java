package com.example.listatarefas.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonplaceholderApi {

    @GET("posts")
    Call<List<Posts>> getAllPosts();

    @GET("albums")
    Call<List<Albums>> getAllAlbums();

    @GET("todos")
    Call<List<Todos>> getTodos();

    @GET("comments")
    Call<List<Comments>> getAllComments();

}
