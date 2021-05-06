package com.example.bahasamata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;


public class Pasien extends AppCompatActivity{

    ImageButton emergencyCall_button, alert_button, alarm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pasien);



        emergencyCall_button = (ImageButton)findViewById(R.id.button_telfonDarurat);
        emergencyCall_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pasien.this, "TELFON DARURAT!", Toast.LENGTH_SHORT).show();
            }
        });

        alert_button = (ImageButton)findViewById(R.id.button_alert);
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pasien.this, "ALERT!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Pasien.this, Alert.class));
            }
        });

        alarm_button = (ImageButton)findViewById(R.id.button_alarm);
        alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pasien.this, "ALARM NYALA!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}