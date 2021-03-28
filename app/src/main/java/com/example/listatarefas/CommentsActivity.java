package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.example.listatarefas.model.Comments;
import com.example.listatarefas.model.JsonplaceholderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    private TextView mjsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        mjsonTxtView = findViewById(R.id.jsonText);
        getComments();

    }

    private void getComments(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonplaceholderApi jsonplaceholderApi = retrofit.create(JsonplaceholderApi.class);
        Call<List<Comments>> call = jsonplaceholderApi.getComments();

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if(!response.isSuccessful()){
                    mjsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Comments> commentsList = response.body();

                for(Comments comments : commentsList){
                    String content = "";
                    content += "postId:" + comments.getPostId() + "\n";
                    content += "id:" + comments.getId() + "\n";
                    content += "name:" + comments.getName() + "\n";
                    content += "email:" + comments.getEmail() + "\n";
                    content += "body:" + comments.getBody() + "\n\n";
                    mjsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                mjsonTxtView.setText(t.getMessage());
            }
        });

    }

}