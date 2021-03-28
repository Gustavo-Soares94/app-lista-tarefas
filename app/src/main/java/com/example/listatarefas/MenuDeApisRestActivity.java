package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuDeApisRestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_de_apis_rest);
    }

    public void irPost(View view){
        Intent intent5 = new Intent(getApplicationContext(), PostsActivity.class);
        startActivity(intent5);
    }
    public void irAlbums(View view){
        Intent intent5 = new Intent(getApplicationContext(), AlbumsActivity.class);
        startActivity(intent5);
    }
    public void irTodos(View view){
        Intent intent5 = new Intent(getApplicationContext(), TodosActivity.class);
        startActivity(intent5);
    }

    public void irComments(View view){
        Intent intent5 = new Intent(getApplicationContext(), CommentsActivity.class);
        startActivity(intent5);
    }

}