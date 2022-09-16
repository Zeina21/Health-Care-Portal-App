package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import VitalSignsPage.VitalSigns;

public class SecondPage extends AppCompatActivity {
String Name,DocName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        Name = getIntent().getStringExtra("fullName");
        DocName = getIntent().getStringExtra("DocName");
    }


    public void Medicament (View V){
        Intent i = new Intent(getApplicationContext(), Medicament.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

    public void Pathstate (View V){
        Intent i = new Intent(getApplicationContext(), PathalogicalState.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

    public void Immunization (View V){
        Intent i = new Intent(getApplicationContext(), Immunization.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

    public void VitalSigns (View V){
        Intent i = new Intent(getApplicationContext(), VitalSigns.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

    public void Allergies (View V){
        Intent i = new Intent(getApplicationContext(), Allergies.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }


    public void history (View V){
        Intent i = new Intent(getApplicationContext(),MedicalHistory.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }


    public void Radiology (View V){
        Intent i = new Intent(getApplicationContext(),Laboratary.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }


    public void Covid (View V){
        Intent i = new Intent(getApplicationContext(), Covid.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

    public void summary (View V){
        Intent i = new Intent(getApplicationContext(), MedicalRecord.class);
        i.putExtra("fullName", Name);
        i.putExtra("DocName",DocName);
        startActivity(i);

    }

}