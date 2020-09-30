package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        final ImageView worker =(ImageView)findViewById(R.id.worker);
        final ImageView doctor =(ImageView)findViewById(R.id.doctor);
        doctor.setVisibility(View.VISIBLE);
        doctor.animate().rotationY(-180).setDuration(500);
        final ImageView police =(ImageView)findViewById(R.id.police);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doctor.setVisibility(View.GONE);

                police.setVisibility(View.VISIBLE);
                police.animate().rotationY(-180).setDuration(500);
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                police.setVisibility(View.GONE);
                worker.setVisibility(View.VISIBLE);
                worker.animate().rotationY(-180).setDuration(500);
            }
        }, 1000);










        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                startActivity(mainIntent);
                splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}