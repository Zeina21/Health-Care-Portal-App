package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.MedicamentC;
import RecycleView.RecycleMed;

public class Medicament extends AppCompatActivity {

    EditText etName, etDose, etFrequency;
    String Name,DocName;
    // creating a variable for our Database Reference for Firebase.
    DatabaseReference medicamentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);


        Name = getIntent().getStringExtra("fullName");
        etName = (EditText) findViewById(R.id.MedName);
        etDose = (EditText) findViewById(R.id.MedDose);
        etFrequency = (EditText) findViewById(R.id.MedFreq);
        DocName = getIntent().getStringExtra("DocName");

        // below line is used to get reference of our database.
        medicamentDB = FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Medications");

        // calling method for getting data.


    }

    public void SaveMed(View V) {
        String name = etName.getText().toString();
        String Dose = etDose.getText().toString();
        String Frequency = etFrequency.getText().toString();


        MedicamentC medicament = new MedicamentC(name, Dose, Frequency,DocName);
        medicamentDB.push().setValue(medicament); //passing the object created above, if we don't use push methos the data will be overridden
        Toast.makeText(Medicament.this, "Data inserted", Toast.LENGTH_SHORT).show();

    }


    public void ViewMed(View v) {
        Intent i = new Intent(getApplicationContext(), RecycleMed.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }
}




