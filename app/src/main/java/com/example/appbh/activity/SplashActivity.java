package com.example.appbh.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.appbh.R;

import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appname = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);
        Paper.init(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Paper.book().read("user")== null){
                    Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        },3000);
    }
}