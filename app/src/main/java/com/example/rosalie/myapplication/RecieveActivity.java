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

import org.json.JSONException;
import org.json.JSONObject;

public class RecieveActivity extends AppCompatActivity {
    //Instantiate the RequestQueue.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);
        final TextView mTextView = (TextView) findViewById(R.id.textView4);
        //Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(RecieveActivity.this);
        String url =ApiUrl.URL_GET_KEY;

       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //convert response to a json object
                            JSONObject resObj = new JSONObject(response);
                            //get the value of the key index in string formate
                            String res = resObj.getString("key");
                            //display the key
                            mTextView.setText(res);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    }

    public void onClick(){
        //TODO: move back to main activity
        Intent intObj = new Intent(RecieveActivity.this, MainActivity.class);
        startActivity(intObj);
    }

}
