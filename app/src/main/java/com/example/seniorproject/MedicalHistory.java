package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.HistoryC;
import RecycleView.RecycleMH;

public class MedicalHistory extends AppCompatActivity {

    EditText OpName,Body,OpFacility,Date;
    DatabaseReference HistoryDB;
    CheckBox General,Local;
    String Name,DocName,Anesthesia="Loco-Regional";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);


        Name = getIntent().getStringExtra("fullName");
        DocName = getIntent().getStringExtra("DocName");

        OpName=(EditText)findViewById(R.id.operation);
        Body=(EditText)findViewById(R.id.location);
        OpFacility=(EditText)findViewById(R.id.medFacility);
        Date=(EditText)findViewById(R.id.medDate);
        General=findViewById(R.id.general);
        Local=findViewById(R.id.local);

        HistoryDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Medical History");


if(General.isChecked()){
    Anesthesia="General";
}



    }


    public void SaveO (View v){

        String OName=OpName.getText().toString();
        String BodyLoc=Body.getText().toString();
        String Facility=OpFacility.getText().toString();
        String OpDate=Date.getText().toString();

        HistoryC history=new HistoryC(OName,BodyLoc,OpDate,Anesthesia,Facility,DocName);
        HistoryDB.push().setValue(history); //passing the object created above, if we don't use push methos the data will be overridden
        Toast.makeText(MedicalHistory.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }

    public void ViewO (View v){
        Intent i = new Intent(getApplicationContext(), RecycleMH.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

}