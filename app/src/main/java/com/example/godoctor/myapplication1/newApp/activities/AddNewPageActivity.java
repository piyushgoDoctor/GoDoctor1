package com.example.godoctor.myapplication1.newApp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;
import com.example.godoctor.myapplication1.newApp.models.Person;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class AddNewPageActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 100;
    String personName;
    ArrayList<Person> persons;

    byte[] imageBite;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_page);

        final String id = getIntent().getStringExtra("id");

        persons = new ArrayList<>();
        persons = new DbHelper(AddNewPageActivity.this).getAllPersons();

        Button submit = (Button) findViewById(R.id.submit);
        Button scan = (Button) findViewById(R.id.button2);
        final EditText summery = (EditText) findViewById(R.id.addNewPageSummery);
//        TextView category= (TextView) findViewById(R.id.addNewPageCategory);
        Spinner spinner = (Spinner) findViewById(R.id.addNewPageSpinner);
        ArrayList<String> person = new DbHelper(AddNewPageActivity.this).getAllPersonsName();
        spinner.setAdapter(new ArrayAdapter<>(AddNewPageActivity.this, android.R.layout.simple_list_item_1, person));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personName = persons.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dispatchTakePictureIntent();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Submit button","clicked");

                if (!( bitmap == null && summery.getText().toString().isEmpty())) {
                    boolean b = new DbHelper(AddNewPageActivity.this).insertPage(personName, id, summery.getText().toString(), imageBite);
                    if (b) {
                        Toast.makeText(AddNewPageActivity.this, "Page Created SuccessFully.", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(AddNewPageActivity.this, "Some Problem Occur!!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent cropIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
//        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (cropIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cropIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            imageBite=getBitmapAsByteArray(bitmap);
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}
