package com.example.bahasamata;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AlarmListActivity extends AppCompatActivity {

    private RecyclerView revAlarm;
    private FloatingActionButton btn_floating_alarm;
    private AlarmAdapter alarmAdapter;
    private List<Alarmlist> list = new ArrayList<>();
    Dialog dialogdetailalarm;
    EditText et_keteranganalarm;
    Button btn_simpandetail_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listalarm);
        revAlarm = findViewById(R.id.rv_revAlarm);
        btn_floating_alarm = findViewById(R.id.btn_floating_alarm);
        alarmAdapter = new AlarmAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        revAlarm.setLayoutManager(linearLayoutManager);
        revAlarm.setAdapter(alarmAdapter);
        dialogdetailalarm = new Dialog(this);
        dialogdetailalarm.setContentView(R.layout.dialog_detailalarm);
        dialogdetailalarm.setCancelable(true);
        btn_simpandetail_alarm = dialogdetailalarm.findViewById(R.id.btn_simpandetail_alarm);
        et_keteranganalarm = dialogdetailalarm.findViewById(R.id.et_keteranganalarm);

        btn_floating_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialogdetailalarm.show();
            }
        });

        btn_simpandetail_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_keteranganalarm = dialogdetailalarm.findViewById(R.id.et_keteranganalarm);
                list.add(new Alarmlist(et_keteranganalarm.getText().toString(),10,11));
                dialogdetailalarm.hide();
                alarmAdapter.notifyDataSetChanged();
            }
        });
    }
}
