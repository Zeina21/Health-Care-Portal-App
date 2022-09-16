package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.ImmunizationC;
import RecycleView.MainActivity2;

public class Immunization extends AppCompatActivity {
    EditText etname,etDate,etFacility;
    Button SaveI;
    DatabaseReference ImmunizationtDB;
    String Name,DocName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immunization);

        Name = getIntent().getStringExtra("fullName");
        DocName = getIntent().getStringExtra("DocName");

        etname=(EditText)findViewById(R.id.ImuName);
        etDate=(EditText)findViewById(R.id.ImuDate);
        etFacility=(EditText)findViewById(R.id.Facility);
        SaveI=(Button)findViewById(R.id.SaveIm);



        ImmunizationtDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Immunization");


    }

    public void SaveImmu(View V){
        String name = etname.getText().toString();
        String Date = etDate.getText().toString();
        String Facility =etFacility.getText().toString();
        //for spinner .getSelectedItem().toString();

        ImmunizationC immunization =new ImmunizationC(name,Date,Facility,DocName);
        ImmunizationtDB.push().setValue(immunization); //passing the object created above, if we don't use push methos the data will be overridden
        Toast.makeText(Immunization.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }
    public void ViewImmu(View V){
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }
}