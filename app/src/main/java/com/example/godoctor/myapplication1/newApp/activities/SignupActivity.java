package com.example.godoctor.myapplication1.newApp.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;
import com.example.godoctor.myapplication1.newApp.models.Signup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SignupActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CROP_IMAGE = 2;
    File file;
    ImageView yourImage;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
         yourImage= (ImageView) findViewById(R.id.yourImage);
        try {

            AssetManager assetManager = getAssets();
            InputStream istr = assetManager.open("ic_launcher.9.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            yourImage.setImageBitmap(bitmap);

//         String[] str=   getAssets().list("ic_launcher.9.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final EditText yourName= (EditText) findViewById(R.id.yourName);
        final EditText yourDes= (EditText) findViewById(R.id.yourDesc);
        Button signup= (Button) findViewById(R.id.yourSignup);

        yourDes.setHint("Enter Your Description here.");
        yourDes.setLines(5);

        yourImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dispatchTakePictureIntent();
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(yourName.getText().toString().isEmpty() && yourDes.getText().toString().isEmpty())){

                    boolean b=new DbHelper(SignupActivity.this).insertSignup(yourName.getText().toString(),yourDes.getText().toString(),imageUrl);
                    if(b){
                        Toast.makeText(SignupActivity.this,"Signup succesfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignupActivity.this,NewMainActivity.class));
                    }else{
                        Toast.makeText(SignupActivity.this,"There is some problem in signup. Please Try Again.",Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(SignupActivity.this,"All Fields are mandatory.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void dispatchTakePictureIntent() {
        Intent cropIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (cropIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cropIntent, REQUEST_IMAGE_CAPTURE);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            performCrop();
        } else {
            if (data != null) {
                Bundle extras = data.getExtras();

                Bitmap imageBitmap = (Bitmap) extras.get("data");

                try {

                    // create a File object for the parent directory
                    File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/.Piyush");
                    // have the object build the directory structure, if needed.

                    if (!wallpaperDirectory.exists())
                        wallpaperDirectory.mkdirs();

                    //Capture is folder name and file name with date and time
                    imageUrl = wallpaperDirectory.toString() + File.separator + "img_" + System.currentTimeMillis() + ".JPEG";

                    FileOutputStream fileOutputStream = new FileOutputStream(imageUrl);


                    // Here we Resize the Image ...
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    assert imageBitmap != null;
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                            byteArrayOutputStream); // bm is the bitmap object
                    byte[] bsResized = byteArrayOutputStream.toByteArray();


                    fileOutputStream.write(bsResized);
                    fileOutputStream.close();

                    yourImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void performCrop() {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(Uri.fromFile(file), "image/*");
            //            cropIntent.putExtra("data",imageBitmap);
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 2);
//            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_IMAGE);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
