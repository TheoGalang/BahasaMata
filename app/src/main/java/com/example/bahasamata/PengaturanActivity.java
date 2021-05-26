package com.example.bahasamata;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PengaturanActivity extends AppCompatActivity {

    ImageButton btn_settingKontak, btn_settingAlarm;
    Button btn_simpandetail_kontakDarurat;
    TextView tv_idPasien, tv_namaKontak, tv_nomorKontak;
    EditText et_namaKontak, et_nomorKontak;
    private Dialog dialogkontakDarurat;
    private Pasienlist pasienlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaturan);

        pasienlist = getIntent().getParcelableExtra("pasien");

        btn_settingKontak = (ImageButton)findViewById(R.id.btn_settingsND);
        btn_settingAlarm = (ImageButton)findViewById(R.id.btn_settings_alarm);
        tv_namaKontak = (TextView)findViewById(R.id.tv_namaKontak);
        tv_nomorKontak = (TextView)findViewById(R.id.tv_nomorKontak);

        dialogkontakDarurat = new Dialog(this);
        dialogkontakDarurat.setContentView(R.layout.dialog_nomordarurat);
        dialogkontakDarurat.setCancelable(true);
        et_nomorKontak = dialogkontakDarurat.findViewById(R.id.et_nomorKontak);
        et_namaKontak = dialogkontakDarurat.findViewById(R.id.et_namaKontak);
        btn_simpandetail_kontakDarurat = dialogkontakDarurat.findViewById(R.id.btn_simpandetail_kontakDarurat);

        btn_simpandetail_kontakDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_namaKontak.setText(et_namaKontak.getText().toString());
                tv_nomorKontak.setText(et_nomorKontak.getText().toString());
                dialogkontakDarurat.hide();
            }
        });


        tv_idPasien = (TextView)findViewById(R.id.tv_idPasien);

        if (pasienlist != null){
            tv_idPasien.setText(String.valueOf(pasienlist.idpasien));
        }


    }

    public void onClickSettingND(View view ){

        dialogkontakDarurat.show();
    }

    public void onClickStringAlarm(View view){
        startActivity(new Intent(getApplicationContext(), AlarmListActivity.class));
    }

}
