package com.example.bahasamata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bahasamata.model.ModelLogin;
import com.example.bahasamata.remote.APIUtils;
import com.example.bahasamata.remote.UserService;

import java.time.chrono.MinguoChronology;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    ImageButton login;
    TextView daftar;
    UserService userService;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    RadioButton rbPasien, rbPerawat;

    private static final String MyPREFERENCES = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        editor =sharedpreferences.edit();
//        String isLogin = sharedpreferences.getString("isLogin",null);
//        String isType = sharedpreferences.getString("type",null);
//        if(isLogin.equals(null) && isType.equals(null)){
//            Toast.makeText(MainActivity.this
//                    , "Log Out"
//                    , Toast.LENGTH_SHORT).show();
//
//        }else{
//
//            Intent intent = new Intent(MainActivity.this, Pasien.class);
//            startActivity(intent);
//        }

       
        userService = APIUtils.getUserService();
        rbPasien = (RadioButton)findViewById(R.id.radioButton_pasien);
        rbPerawat = (RadioButton)findViewById(R.id.radioButton_perawat);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = (ImageButton)findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rUsername = username.getText().toString();
                String rPassword = password.getText().toString();

                if (!rbPasien.isChecked()&&!rbPerawat.isChecked()){
                    Toast.makeText(MainActivity.this,
                            "Anda belum memilih login sebagai apa",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String rType = "";

                if (rbPasien.isChecked()){
                    rType = "1";
                }
                else{
                    rType = "0";
                }

                Call<ModelLogin> call = userService.login(rUsername, rPassword, rType);
                call.enqueue(new Callback<ModelLogin>() {
                    @Override
                    public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                        if (response.isSuccessful()) {
                            String checkResponse = response.body().getData() + "";
                            int i = 0;
                            if (!checkResponse.equals("null")) {
                                editor.putString("isLogin", "true");
                                editor.putString("id", response.body().getData().get(i).getId());
                                editor.putString("type", "0");
                                editor.commit();
                                Intent intent = new Intent(MainActivity.this, PasienlistActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this
                                        , response.body().getMessage()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelLogin> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });

            }
        });

        daftar = (TextView)findViewById(R.id.tbDaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlarmListActivity.class));
            }
        });

    }
}
