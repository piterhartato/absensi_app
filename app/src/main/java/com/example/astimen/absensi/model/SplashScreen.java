package com.example.astimen.absensi.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.astimen.absensi.MainActivity;
import com.example.astimen.absensi.R;
//import java.util.logging.Handler;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private String uid;
    private String username,nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        uid = getIntent().getStringExtra("uid");
        username = getIntent().getStringExtra("username");
        nama= getIntent().getStringExtra("nama");

        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu= new Intent(SplashScreen.this,MainActivity.class);
                mainMenu.putExtra("uid", uid);
                mainMenu.putExtra("username", username);
                mainMenu.putExtra("nama", nama);
                startActivity(mainMenu);
                finish();
                overridePendingTransition(R.layout.fadein,R.layout.fadeout);
            }
        }, 2000);

    }
}
