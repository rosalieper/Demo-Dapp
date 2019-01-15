package com.example.rosalie.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   // public static final String EXTRA_MESSAGE = "AMOUNT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //when send button clicked
    public void onSend( View v){
        Intent intObj = new Intent(MainActivity.this, SendActivity.class);
        TextView message = (TextView) findViewById(R.id.editText2);
        String amount = message.getText().toString();
        intObj.putExtra("AMOUNT", amount);
        startActivity(intObj);
    }
    //when recieve button clicked
    public void onRecieve(View v){
        Intent intObj = new Intent(MainActivity.this, RecieveActivity.class);
        startActivity(intObj);
    }
    //when generate button clicked
    public void onGenerate(View v){
        Intent intent = new Intent(MainActivity.this, GenerateActivity.class);
        startActivity(intent);
    }

}
