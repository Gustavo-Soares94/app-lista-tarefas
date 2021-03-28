package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.listatarefas.model.JsonplaceholderApi;
import com.example.listatarefas.model.Todos;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodosActivity extends AppCompatActivity {

    private TextView mjsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        mjsonTxtView = findViewById(R.id.jsonText);
        getTodos();
    }

    private void getTodos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonplaceholderApi jsonplaceholderApi = retrofit.create(JsonplaceholderApi.class);
        Call<List<Todos>> call = jsonplaceholderApi.getTodos();

        call.enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {

                if(!response.isSuccessful()){
                    mjsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Todos> todosList = response.body();

                for(Todos todos : todosList){
                    String content = "";
                    content += "userId:" + todos.getUserId() + "\n";
                    content += "id:" + todos.getId() + "\n";
                    content += "title:" + todos.getTitle() + "\n";
                    content += "completed:" + todos.isCompleted() + "\n\n";
                    mjsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {
                mjsonTxtView.setText(t.getMessage());
            }
        });

    }
}