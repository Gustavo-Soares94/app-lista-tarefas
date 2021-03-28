package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.listatarefas.model.JsonplaceholderApi;
import com.example.listatarefas.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity {

    private TextView mjsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        mjsonTxtView = findViewById(R.id.jsonText);
        getPosts();
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonplaceholderApi jsonplaceholderApi = retrofit.create(JsonplaceholderApi.class);
        Call<List<Posts>> call = jsonplaceholderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mjsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Posts> postsList = response.body();

                for(Posts post : postsList){
                    String content = "";
                    content += "userId:" + post.getUserId() + "\n";
                    content += "id:" + post.getId() + "\n";
                    content += "title:" + post.getTitle() + "\n";
                    content += "body:" + post.getBody() + "\n\n";

                    mjsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mjsonTxtView.setText(t.getMessage());
            }
        });

    }

}