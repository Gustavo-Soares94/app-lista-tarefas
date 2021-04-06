package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.listatarefas.model.Albums;
import com.example.listatarefas.model.AlbumsAdapter;
import com.example.listatarefas.model.ApiJsonplaceholder;
import com.example.listatarefas.model.Posts;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsActivity extends AppCompatActivity implements AlbumsAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;

    AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        albumsAdapter = new AlbumsAdapter(this::ClickedAlbums);

        getAllAlbums();
    }

    public void getAllAlbums(){
        Call<List<Albums>> albumsList = ApiJsonplaceholder.getAlbums().getAllAlbums();

        albumsList.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if(response.isSuccessful()){
                    List<Albums> albums = response.body();
                    albumsAdapter.setData(albums);
                    recyclerView.setAdapter(albumsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void ClickedAlbums(Albums albums) {

        startActivity(new Intent(this,AlbumsDetailsActivity.class).putExtra("data", albums));
    }

}