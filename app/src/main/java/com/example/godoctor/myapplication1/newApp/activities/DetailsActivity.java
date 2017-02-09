package com.example.godoctor.myapplication1.newApp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String name=getIntent().getStringExtra("name");
        String des=getIntent().getStringExtra("des");
        TextView details= (TextView) findViewById(R.id.details);
        details.setText(name+"\n..........................................................................................." +
                "\n"+des);
        Button cNewPage= (Button) findViewById(R.id.cNewPage);
        cNewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this,"Don't know what to do?",Toast.LENGTH_LONG).show();
            }
        });
    }
}