package com.example.bahasamata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Daftar extends AppCompatActivity{

    EditText nama_daftar, username_daftar, password_daftar, konfirmasip_daftar;
    RadioButton pasien, perawat;
    ImageButton daftar_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        daftar_button = (ImageButton) findViewById(R.id.button_daftar);
        daftar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Daftar.this, "Akun Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
            }
        });

    }
}
