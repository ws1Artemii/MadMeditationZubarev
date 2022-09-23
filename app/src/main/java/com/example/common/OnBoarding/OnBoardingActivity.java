package com.example.common.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.common.Login.LoginActivity;
import com.example.common.R;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}