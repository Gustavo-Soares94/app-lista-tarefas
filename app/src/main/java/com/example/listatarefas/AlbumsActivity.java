package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.listatarefas.model.Albums;
import com.example.listatarefas.model.JsonplaceholderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsActivity extends AppCompatActivity {

    private TextView mjsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        mjsonTxtView = findViewById(R.id.jsonText);
        getAlbums();
    }

    private void getAlbums(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonplaceholderApi jsonplaceholderApi = retrofit.create(JsonplaceholderApi.class);
        Call<List<Albums>> call = jsonplaceholderApi.getAlbums();

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {

                if(!response.isSuccessful()){
                    mjsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Albums> albumsList = response.body();

                for(Albums albums : albumsList){
                    String content = "";
                    content += "userId:" + albums.getUserId() + "\n";
                    content += "id:" + albums.getId() + "\n";
                    content += "title:" + albums.getTitle() + "\n\n";
                    mjsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                mjsonTxtView.setText(t.getMessage());
            }
        });

    }
}