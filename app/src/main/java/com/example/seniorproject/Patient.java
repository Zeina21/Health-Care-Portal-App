package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import VitalSignsPage.VitalSigns;

public class Patient extends AppCompatActivity {
String Name;
String DocName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Name = getIntent().getStringExtra("fullName");
        DocName=getIntent().getStringExtra("DocName");

    }


    public void Vital (View V){
        Intent i = new Intent(getApplicationContext(), VitalSigns.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName", Name);
        startActivity(i);

    }

        public void Covid19 (View V){
            Intent i = new Intent(getApplicationContext(), Covid19.class);
            i.putExtra("fullName", Name);
            i.putExtra("DocName", Name);
            startActivity(i);

        }


    public void Summary (View V){
        Intent i = new Intent(getApplicationContext(), MedicalRecord.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName", Name);
        startActivity(i);

    }


}