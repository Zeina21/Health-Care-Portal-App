package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Laboratary extends AppCompatActivity {

    private final int CHOOSE_PDF_FROM_DEVICE = 1001;
    private static final String TAG = "Radiology";
    private ImageButton chooseFile;
    private TextView path_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratary);

        chooseFile = findViewById(R.id.chooseFile);
        path_tv = findViewById(R.id.path_tv);

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callchoosefile();
            }
        });
    }

    private void callchoosefile() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("application/pdf");
        startActivityForResult(i, CHOOSE_PDF_FROM_DEVICE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent resultData) {
        super.onActivityResult(requestCode, resultcode, resultData);
        if (requestCode == CHOOSE_PDF_FROM_DEVICE && resultcode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that the user selected.
            if (resultData != null) {
                //  Here we will display the path_or uri_of that file
                Log.d(TAG, "onActivityResult: " + resultData.getData());
                path_tv.setText("File Path: " + resultData.getData());

            }
        }
    }


}