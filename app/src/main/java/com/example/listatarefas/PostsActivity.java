package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import com.example.listatarefas.model.ApiJsonplaceholder;
import com.example.listatarefas.model.Comments;
import com.example.listatarefas.model.CommentsAdapter;
import com.example.listatarefas.model.JsonplaceholderApi;
import com.example.listatarefas.model.Posts;
import com.example.listatarefas.model.PostsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity implements PostsAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;

    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        postsAdapter = new PostsAdapter(this::ClickedPosts);

        getAllPosts();

    }

    public void getAllPosts(){
        Call<List<Posts>> postsList = ApiJsonplaceholder.getPost().getAllPosts();

        postsList.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful()){
                    List<Posts> posts = response.body();
                    postsAdapter.setData(posts);
                    recyclerView.setAdapter(postsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void ClickedPosts(Posts posts) {

        startActivity(new Intent(this,PostsDetailsActivity.class).putExtra("data", posts));
    }


}