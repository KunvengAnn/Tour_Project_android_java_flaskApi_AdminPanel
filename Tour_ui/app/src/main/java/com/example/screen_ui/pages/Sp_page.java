package com.example.screen_ui.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.screen_ui.MainActivity;
import com.example.screen_ui.R;

public class Sp_page extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the Sp_page duration, start the main activity
                Intent mainIntent = new Intent(Sp_page.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
    // end OnCreate
}
