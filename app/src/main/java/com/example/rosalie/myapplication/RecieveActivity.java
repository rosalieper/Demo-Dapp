package com.example.rosalie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RecieveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        Intent intent = getIntent();
    }

    public void onClick(){
        Intent intObj = new Intent(RecieveActivity.this, EndActivity.class);
        startActivity(intObj);
    }
}
