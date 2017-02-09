package com.example.godoctor.myapplication1.newApp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;

public class AddNewPersonActivity extends AppCompatActivity {

    String arr[]=new String[]{"Family","Friends","Other"};

    String cat="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
        Spinner sp= (Spinner) findViewById(R.id.addNewSpinner);
        final EditText name= (EditText) findViewById(R.id.addNewName);
        final EditText des= (EditText) findViewById(R.id.addNewDes);
        final EditText rel= (EditText) findViewById(R.id.addNewRelation);

        sp.setAdapter(new ArrayAdapter<>(AddNewPersonActivity.this, android.R.layout.simple_list_item_1, arr));

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat=arr[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button submit= (Button) findViewById(R.id.addNewSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(name.getText().toString().isEmpty() && des.getText().toString().isEmpty() && rel.getText().toString().isEmpty())){
              boolean b=  new DbHelper(AddNewPersonActivity.this).insertPersons(name.getText().toString(),des.getText().toString(),rel.getText().toString(),cat);
                if(b){

                    Toast.makeText(AddNewPersonActivity.this,"Registration successful.",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(AddNewPersonActivity.this,NewMainActivity.class);
                    startActivity(in);
                    finish();

                }else{
                    Toast.makeText(AddNewPersonActivity.this,"Some Error In Database.",Toast.LENGTH_LONG).show();
                }
                }else{
                    Toast.makeText(AddNewPersonActivity.this,"Please Fill All The Entries",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
