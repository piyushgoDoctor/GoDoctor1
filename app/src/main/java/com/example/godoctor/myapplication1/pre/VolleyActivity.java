package com.example.godoctor.myapplication1.pre;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.godoctor.myapplication1.R;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);


        for (int i = 0; i < 1000000; i++) {
            String tag_string_req = "string_req";

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.43.153:8085/ProjectWithMongoDb/SignUpAct?category=dhjguhdnjbsajjasjfsjkbfa" +
                    "kskjkjsfkjsfs" +

                    "name=klsks&description=sjdjd&relation=skklds";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("res",response);
                            // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
    }
}
