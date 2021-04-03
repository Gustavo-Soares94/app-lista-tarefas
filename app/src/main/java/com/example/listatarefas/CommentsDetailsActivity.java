package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listatarefas.model.Comments;

public class CommentsDetailsActivity extends AppCompatActivity {

    TextView commentsEmail,commentsName,commentsBody;
    Comments comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_details);

        commentsEmail = findViewById(R.id.commentsEmail);
        commentsName = findViewById(R.id.commentsName);
        commentsBody = findViewById(R.id.commentsBody);

        Intent intent = getIntent();
        if(intent.getExtras() !=null) {

            comments =(Comments)  intent.getSerializableExtra("data");
            String email = comments.getEmail();
            String name = comments.getName();
            String body = comments.getBody();

            commentsEmail.setText(email);
            commentsName.setText(name);
            commentsBody.setText(body);
        }

    }
}