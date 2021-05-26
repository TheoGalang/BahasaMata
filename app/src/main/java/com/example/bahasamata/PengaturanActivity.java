package com.example.bahasamata;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PengaturanActivity extends AppCompatActivity {

    ImageButton btn_settingKontak, btn_settingAlarm;
    private Dialog kontakDarurat, alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaturan);

        btn_settingKontak = (ImageButton)findViewById(R.id.btn_settingsND);
        btn_settingAlarm = (ImageButton)findViewById(R.id.btn_settings_alarm);




    }
}
