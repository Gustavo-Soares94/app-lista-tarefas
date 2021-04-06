package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listatarefas.model.Albums;
import com.example.listatarefas.model.Posts;

public class AlbumsDetailsActivity extends AppCompatActivity {

    TextView albumsTitle;
    Albums albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_details);

        albumsTitle = findViewById(R.id.albumsTitle);

        Intent intent = getIntent();
        if(intent.getExtras() !=null) {

            albums = (Albums) intent.getSerializableExtra("data");
            String title = albums.getTitle();

            albumsTitle.setText(title);

        }
    }
}