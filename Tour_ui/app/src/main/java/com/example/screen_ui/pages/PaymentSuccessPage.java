package com.example.screen_ui.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.screen_ui.MainActivity;
import com.example.screen_ui.R;

public class PaymentSuccessPage extends AppCompatActivity {
    private Button btnGOHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_success);

        BtnGoHome();
    }
    // end of OnCreate

    private void BtnGoHome(){
        btnGOHome = findViewById(R.id.id_btnHomePaymentSuccess);

        btnGOHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
