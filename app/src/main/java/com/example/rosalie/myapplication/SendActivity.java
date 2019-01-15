package com.example.rosalie.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class SendActivity extends AppCompatActivity {
    String amount;
    String adress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        amount = intent.getStringExtra("AMOUNT");
        EditText editText = findViewById(R.id.editText3);
        adress = editText.getText().toString();

        TextView textView = findViewById(R.id.textView6);
        textView.setText(amount);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                RequestQueue queue = Volley.newRequestQueue(SendActivity.this);
                //TODO: replace the variable url with the ones available in ApiUrl
                String url = "http://192.168.8.102:3000/send";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                //TODO: Get the string from the response and display it on a new intent
                                Intent intObj = new Intent(SendActivity.this, EndActivity.class);
                                intObj.putExtra("RESPONSE", response);
                                startActivity(intObj);
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Intent intObj = new Intent(SendActivity.this, EndActivity.class);
                                intObj.putExtra("RESPONSE", error.toString());
                                startActivity(intObj);
                                Log.d("ERROR.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        //TODO: get the parameters from the user input. amount and adress.
                        params.put("amount", amount);
                        params.put("address", adress);
                        Log.d("AMOUNT", amount);
                        Log.d("ADDRESS", adress);

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

    }


}
