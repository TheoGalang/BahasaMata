package com.example.bahasamata;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasienlistActivity extends AppCompatActivity {

    private RecyclerView revPasien;
    private FloatingActionButton btn_floating;
    private CardView cv_pasienlist;
    private PasienAdapter pasienlistAdapter;
    private Dialog dialogtambahpasien,dialogdetailpasien;
    private List<Pasienlist> list = new ArrayList<>();
    EditText namapasiendialog,idpasiendialog;
    Random nomoracak;
    private Button btn_tambah_pasien,btn_cancel_pasien,btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpasien);

        nomoracak = new Random();
        cv_pasienlist = findViewById(R.id.cv_listpasien);
        dialogdetailpasien = new Dialog(this);
        dialogdetailpasien.setContentView(R.layout.dialog_detailpasien);
        dialogdetailpasien.setCancelable(true);
        dialogtambahpasien = new Dialog(this);
        dialogtambahpasien.setContentView(R.layout.dialog_tambahpasien);
        dialogtambahpasien.setCancelable(true);
        btn_tambah_pasien = dialogtambahpasien.findViewById(R.id.btn_tambah_pasien);
        btn_cancel_pasien = dialogtambahpasien.findViewById(R.id.btn_cancel_tambahpasien);
        btn_simpan = dialogdetailpasien.findViewById(R.id.btn_simpandetail_pasien);



        revPasien = findViewById(R.id.rv_revPasien);
        btn_floating = findViewById(R.id.btn_floating);
        pasienlistAdapter = new PasienAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        revPasien.setLayoutManager(linearLayoutManager);
        revPasien.setAdapter(pasienlistAdapter);




        btn_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtambahpasien.show();


            }
        });
        btn_tambah_pasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namapasiendialog = dialogtambahpasien.findViewById(R.id.et_namapasien);
                list.add(new Pasienlist(namapasiendialog.getText().toString(),nomoracak.nextInt()));
                dialogtambahpasien.hide();
                pasienlistAdapter.notifyDataSetChanged();
            }
        });
        btn_cancel_pasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtambahpasien.hide();
            }
        });


    }

}
