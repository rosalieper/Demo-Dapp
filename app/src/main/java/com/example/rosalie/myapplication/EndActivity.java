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

import org.json.JSONException;
import org.json.JSONObject;

public class EndActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        final TextView mTextView = (TextView) findViewById(R.id.textView2);
        //Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(EndActivity.this);
        //TODO: Change the url variable to use the default in the api package folder
        String url ="http://192.168.8.103:3000/create_account";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        Intent intent = getIntent();

        String message = "Thank you for using our services";
        // Capture the layout's TextView and set the string as its text
       // TextView textView = findViewById(R.id.textView2);
        //textView.setText(message);

    }
}
