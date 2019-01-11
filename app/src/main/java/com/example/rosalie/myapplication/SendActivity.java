package com.example.rosalie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        String index = intent.getStringExtra("AMOUNT");

        TextView textView = (TextView) findViewById(R.id.textView6);
        textView.setText(index);
    }

    public void onSend(View v){

    }

}
