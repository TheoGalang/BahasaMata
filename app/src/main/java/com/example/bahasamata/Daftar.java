package com.example.bahasamata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahasamata.model.ModelRegister;
import com.example.bahasamata.remote.APIUtils;
import com.example.bahasamata.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daftar extends AppCompatActivity{

    EditText name, username, password, konfpass;
    RadioButton pasien, perawat;
    ImageButton daftar;

    UserService userService;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private static final String MyPREFERENCES = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        editor =sharedpreferences.edit();

        String isLogin = sharedpreferences.getString("isLogin",null);
        String isType = sharedpreferences.getString("type",null);
//        if(isLogin.equals(null) && isType.equals(null)){
//            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//            startActivity(intent);
//
//        }

        userService = APIUtils.getUserService();


        name = (EditText) findViewById(R.id.daftar_nama);
        username = (EditText) findViewById(R.id.daftar_username);
        password = (EditText) findViewById(R.id.daftar_password);
        konfpass = (EditText) findViewById(R.id.daftar_password_konfirmasi);

        pasien = (RadioButton) findViewById(R.id.radioButton_pasien);
        perawat = (RadioButton) findViewById(R.id.radioButton_perawat);

        daftar = (ImageButton) findViewById(R.id.button_daftar);

//
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rName = (name.getText().toString());
                String rPassword  = (password.getText().toString());
                String rKonfPass = konfpass.getText().toString();
                String rUsername = (username.getText().toString());
                String rType = "-1";
                if(!pasien.isChecked()&&!perawat.isChecked()){
                    Toast.makeText(Daftar.this
                            , " Anda belum memilih Daftar sebagai "
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pasien.isChecked()){
//                    1 = difabel
                    rType = "1";
                }else if(perawat.isChecked()){
                    rType = "0";
                }

                if(!rPassword.equals(rKonfPass)){
                    Toast.makeText(Daftar.this
                            , " Password Anda Tidak Sama "
                            , Toast.LENGTH_SHORT).show();
                }else {
                    Call<ModelRegister> call = userService.register(rName,rUsername,rPassword,rType);
                    call.enqueue(new Callback<ModelRegister>() {
                        @Override
                        public void onResponse(Call<ModelRegister> call, Response<ModelRegister> response) {
                            if (response.isSuccessful()) {
                                String checkResponse =response.body().getData()+"";
                                if(!checkResponse.equals("null")) {
                                    Intent intent = new Intent(Daftar.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Daftar.this
                                            ,  response.body().getMessage()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Daftar.this, "Tidak berhasil terkoneksi ke server", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ModelRegister> call, Throwable t) {
                            Log.e("ERROR: ", t.getMessage());
                        }
                    });
                }
            }
        });

    }
}
