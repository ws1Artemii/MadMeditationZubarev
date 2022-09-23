package com.example.common.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.common.R;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private class User {
        private String email;
        private String password;

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }

    private interface API {
        @POST("user/login")
        Call<ResponseBody> auth(@Body User user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()) {
                    User u = new User();
                    u.setEmail(email.getText().toString());
                    u.setPassword(password.getText().toString());
                    api.auth(u)
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Log.e("Response", response.raw().toString());
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("ERROW", t.getMessage().toString());
                                }
                            });
                }
            }
        });

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private boolean check() {
        if(email.getText().toString().length() == 0) {
            return false;
        }
        else {
            Pattern p = Pattern.compile("^[a-z0-9]+[@][a-z0-9]+[.][a-z]{2,3}");
            if(!p.matcher(email.getText().toString()).matches()) {
                return false;
            }
        }
        if(password.getText().toString().length() == 0) {
            return false;
        }
        else if(password.getText().toString().length() < 6) {
            return false;
        }

        return true;
    }
}