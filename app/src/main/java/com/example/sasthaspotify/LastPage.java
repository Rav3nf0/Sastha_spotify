package com.example.sasthaspotify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LastPage extends AppCompatActivity {
    Button btnshare, btnback, startButton, stopButton, contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_page);
        btnshare = findViewById(R.id.share);
        btnback = findViewById(R.id.back);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        contact = findViewById(R.id.contacts);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastPage.this, Contacts.class);
                startActivity(intent);
            }
        });

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastPage.this, ShareClass.class);
                startActivity(intent);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(LastPage.this, RunningService.class));
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(LastPage.this, RunningService.class));
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
