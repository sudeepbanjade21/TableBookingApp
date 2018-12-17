package com.example.sudeep.reastroapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();


    }
    public void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceUtils.getInstance().getString("register_user").equalsIgnoreCase("")){
                   //if not registered show login screen
                    Intent intent=new Intent(SplashActivity.this,LogInActivity.class);
                    startActivity(intent);
                }
                else{
                   //if registered show home screen
                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);

                }
                finish();

            }
        },2000);
    }
    }