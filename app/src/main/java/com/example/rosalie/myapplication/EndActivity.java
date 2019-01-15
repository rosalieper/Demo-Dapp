package com.example.rosalie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    // public static final String EXTRA_MESSAGE = "AMOUNT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        String message = intent.getStringExtra("RESPONSE");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }
}
