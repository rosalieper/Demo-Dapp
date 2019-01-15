package com.example.rosalie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rosalie.myapplication.api.ApiUrl;


public class GenerateActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        final TextView mTextView =  findViewById(R.id.textView2);
        //Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(GenerateActivity.this);
        String url =ApiUrl.URL_GENERATE;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String Error = "Sorry!! Something went wrong.";
                mTextView.setText(Error);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        Intent intent = getIntent();


    }
}
