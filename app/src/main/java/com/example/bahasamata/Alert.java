package com.example.bahasamata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class Alert extends AppCompatActivity{

    
    ImageButton matikanAlert_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        MediaPlayer emergencyAlarm_sound = MediaPlayer.create(this, R.raw.emergency_alarm);

        emergencyAlarm_sound.start();
        emergencyAlarm_sound.setLooping(true);



        matikanAlert_button = (ImageButton)findViewById(R.id.button_matikanAlert);
        matikanAlert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emergencyAlarm_sound.stop();

            }
        });



    }
}
