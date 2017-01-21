package com.example.godoctor.myapplication1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.net.URISyntaxException;

public class PdfActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 101;
    private static final String TAG = "PDFACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        WebView webview = (WebView) findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
        String doc="https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwiBgZbmy9LRAhUBuo8KHUHMCtgQFggZMAA&url=https%3A%2F%2Fgymkhana.iiita.ac.in%2Ftenders%2Fdocs%2Fletterlegal5.doc&usg=AFQjCNHcHpefEEOw0Xk5tmlSPAsDyl-O5g&bvm=bv.144224172,d.c2I";
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + doc);

        Button choosePdf= (Button) findViewById(R.id.chooseButton);
        choosePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_GET_CONTENT);
                in.setType("/");
                in.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(
                            Intent.createChooser(in, "Select a File to Upload"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(PdfActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file 
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    String path = null;
                    try {
                        path = FileUtils.getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
