package com.example.crypto_currency_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class after_gs extends AppCompatActivity {

    private Button regi;
    private Button log;
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_gs);

        regi = findViewById(R.id.regisbtn);
        log = findViewById(R.id.log);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(after_gs.this, MainActivity.class));

            }
        });

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(after_gs.this, register.class));

            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(after_gs.this, login.class));

            }
        });
        TextView btn = findViewById(R.id.faq);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(after_gs.this, faq.class));

            }
        });
    }

}