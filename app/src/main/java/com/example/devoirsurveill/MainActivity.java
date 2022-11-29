package com.example.devoirsurveill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button _btnClients,_btnContrats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnClients=(Button) findViewById(R.id.btnClients);
        _btnContrats=(Button) findViewById(R.id.btnContrats);
        _btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gesCli=new Intent(getApplicationContext(),GestionClientActivity.class);
                startActivity(gesCli);
            }
        });
        _btnContrats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contratI=new Intent(getApplicationContext(),ContratsActivity.class);
                startActivity(contratI);
            }
        });
    }
}