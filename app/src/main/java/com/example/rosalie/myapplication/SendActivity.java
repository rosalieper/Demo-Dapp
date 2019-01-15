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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rosalie.myapplication.api.ApiUrl;

import java.util.HashMap;
import java.util.Map;


public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        ApiUrl.amount = intent.getStringExtra("AMOUNT");
        EditText editText = findViewById(R.id.editText3);
        ApiUrl.adress = editText.getText().toString();

        TextView textView = findViewById(R.id.textView6);
        textView.setText(ApiUrl.amount);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // make transaction on click event
                RequestQueue queue = Volley.newRequestQueue(SendActivity.this);
                String url = ApiUrl.URL_SEND;
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // display response message on a new activity
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
                                Log.e("ERROR.Responses", error.toString());
                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  parameters = new HashMap<String, String>();
                        //get parameters from user input
                        parameters.put("amount", ApiUrl.amount);
                        parameters.put("address", ApiUrl.adress);

                        Log.e("AMOUNT you just input", ApiUrl.amount);
                        Log.e("ADDRESS you just input", ApiUrl.adress);

                        return parameters;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("Content-Type","application/x-www-form-urlencoded");
                        return parameters;
                    }
                };
                queue.add(postRequest);
            }
        });

    }


}
