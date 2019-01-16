package com.example.rosalie.myapplication;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        ApiUrl.amount = intent.getStringExtra("AMOUNT");


        TextView textView = findViewById(R.id.textView6);
        textView.setText(ApiUrl.amount);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // make transaction on click event
                RequestQueue queue = Volley.newRequestQueue(SendActivity.this);
                String url = ApiUrl.URL_SEND;
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    //convert response to a json object
                                    JSONObject resObj = new JSONObject(response);
                                    //get the value of the key index in string formate
                                    String res = resObj.getString("key");
                                    //display the key
                                    // display response message on a new activity
                                    Intent intObj = new Intent(SendActivity.this, EndActivity.class);
                                    intObj.putExtra("RESPONSE", res);
                                    startActivity(intObj);
                                    Log.d("Response", res);
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
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
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        //get the adress from the input
                        EditText editText = findViewById(R.id.editText3);
                        ApiUrl.adress = editText.getText().toString();
                        //store parameters from user input in object
                        params.put("amount", ApiUrl.amount);
                        params.put("address", ApiUrl.adress);
                        //debugging
                        Log.e("PARAMS", params.toString());

                        return params;
                    }

                    /*@Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Accept","application/json");
                        params.put("Content-Type","application/json");
                        return params;
                    }*/
                };
                Log.e("POST", postRequest.toString());
                queue.add(postRequest);
            }
        });

    }
}

