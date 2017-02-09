package com.example.godoctor.myapplication1.compression;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.godoctor.myapplication1.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.yovenny.videocompress.MediaController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VideoActivity extends AppCompatActivity {
    private static final int RESULT_CODE_COMPRESS_VIDEO = 3;
    private static final String TAG = "MainActivity";
    private EditText editText;
    private ProgressBar progressBar;
    public static final String APP_DIR = "VideoCompressor";
    public static final String COMPRESSED_VIDEOS_DIR = "/Compressed Videos/";
    public static final String TEMP_DIR = "/Temp/";

    public static void try2CreateCompressDir() {
        File f = new File(Environment.getExternalStorageDirectory(), File.separator + APP_DIR);
        f.mkdirs();
        f = new File(Environment.getExternalStorageDirectory(), File.separator + APP_DIR + COMPRESSED_VIDEOS_DIR);
        f.mkdirs();
        f = new File(Environment.getExternalStorageDirectory(), File.separator + APP_DIR + TEMP_DIR);
        f.mkdirs();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);

        findViewById(R.id.btnSelectVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                startActivityForResult(intent, RESULT_CODE_COMPRESS_VIDEO);
            }
        });
//       try{
//        String link="http://demo.digi-corp.com/S2LWebservice/Resources/SampleVideo.mp4";
//        VideoView videoView = (VideoView) findViewById(R.id.VideoView);
//
//        android.widget.MediaController mediaController=new android.widget.MediaController(VideoActivity.this);
//        mediaController.setAnchorView(videoView);
//        Uri video = Uri.parse(link);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(video);
//        videoView.start();
//    } catch (Exception e) {
//        // TODO: handle exception
//        Toast.makeText(this, "Error connecting", Toast.LENGTH_SHORT).show();
//    }


    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (reqCode == RESULT_CODE_COMPRESS_VIDEO) {
                if (uri != null) {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
                    try {
                        if (cursor != null && cursor.moveToFirst()) {
                            String path=cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA));
                            editText.setText(path);
                        }else {
                            editText.setText(uri.getPath());
                        }
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
            }
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void compress(View v) {
        try2CreateCompressDir();
        String outPath=Environment.getExternalStorageDirectory()
                + File.separator
                + APP_DIR
                + COMPRESSED_VIDEOS_DIR
                +"VIDEO_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".mp4";
        new VideoCompressor().execute(editText.getText().toString(),outPath);
    }

    class VideoCompressor extends AsyncTask<String, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            Log.e(TAG,"Start video compression");
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return MediaController.getInstance().convertVideo(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(Boolean compressed) {
            super.onPostExecute(compressed);
            progressBar.setVisibility(View.GONE);
            if(compressed){
                Log.e(TAG,"Compression successfully!");
            }
        }
    }

}