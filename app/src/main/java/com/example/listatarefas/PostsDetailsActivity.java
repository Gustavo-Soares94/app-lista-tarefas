package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listatarefas.model.Comments;
import com.example.listatarefas.model.Posts;

public class PostsDetailsActivity extends AppCompatActivity {

    TextView postsTitle,postsBody;
    Posts posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_details);

        postsTitle = findViewById(R.id.postsTitle);
        postsBody = findViewById(R.id.postsBody);

        Intent intent = getIntent();
        if(intent.getExtras() !=null) {

            posts = (Posts) intent.getSerializableExtra("data");
            String title = posts.getTitle();
            String body = posts.getBody();

            postsTitle.setText(title);
            postsBody.setText(body);
        }

    }
}