package com.example.listatarefas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.listatarefas.model.ApiJsonplaceholder;
import com.example.listatarefas.model.Comments;
import com.example.listatarefas.model.CommentsAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity implements CommentsAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;

    CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        commentsAdapter = new CommentsAdapter(this::ClickedComments);

        getAllComments();

    }

    public void getAllComments(){
        Call<List<Comments>> commentsList = ApiJsonplaceholder.getComments().getAllComments();

        commentsList.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(response.isSuccessful()){
                    List<Comments> comments = response.body();
                    commentsAdapter.setData(comments);
                    recyclerView.setAdapter(commentsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void ClickedComments(Comments comments) {
        
        startActivity(new Intent(this,CommentsDetailsActivity.class).putExtra("data",comments));
    }
}

