package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // to remove title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // to remove action bar
        getSupportActionBar().hide();
        // create delay effect of 3 seconds to splash activity screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // go from splash activity to MainActivity by waiting 3 seconds
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                // finish the splash activity
                finish();
            }
        }, 3000);
    }
}