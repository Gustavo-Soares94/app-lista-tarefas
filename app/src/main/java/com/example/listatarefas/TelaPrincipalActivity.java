package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

    }

    public void voltarLogin(View view){

        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }

    public void voltarSplash(View view){

        Intent intent2 = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent2);
    }

    public void irMenuApiRest(View view){

        Intent intent3 = new Intent(getApplicationContext(), MenuDeApisRestActivity.class);
        startActivity(intent3);
    }

}