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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahasamata.model.ModelLogin;
import com.example.bahasamata.remote.APIUtils;
import com.example.bahasamata.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    ImageButton login;
    TextView daftar;
    UserService userService;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private static final String MyPREFERENCES = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        editor =sharedpreferences.edit();
        String isLogin = sharedpreferences.getString("isLogin",null);
        String isType = sharedpreferences.getString("type",null);
        if(isLogin.equals(null) && isType.equals(null)){
            Toast.makeText(MainActivity.this
                    , "Log Out"
                    , Toast.LENGTH_SHORT).show();

        }else{

            Intent intent = new Intent(MainActivity.this, Pasien.class);
            startActivity(intent);
        }

        Log.d("asu","value : " + sharedpreferences.getString("type",null));
        userService = APIUtils.getUserService();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        daftar = (TextView) findViewById(R.id.text_button_daftar);

        login = (ImageButton)findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rUsername = username.getText().toString();
                String rPassword = password.getText().toString();
                Call<ModelLogin> call = userService.login(rUsername, rPassword, "0");
//                Toast.makeText(MainActivity.this
//                        , "asu"
//                        , Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(MainActivity.this, Pasien.class);
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


                daftar = (TextView) findViewById(R.id.text_button_daftar);
                daftar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, Daftar.class));
                    }
                });

            }
        });
    }
}
