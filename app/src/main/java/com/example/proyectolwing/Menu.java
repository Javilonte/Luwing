package com.example.proyectolwing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void botonestado(View v){
        Intent intent = new Intent(this, CrearProyecto.class);
        startActivity(intent);
    }

    public void botongestion(View v){
        Intent intent = new Intent(this, BuscarAd.class);
        startActivity(intent);
    }


}