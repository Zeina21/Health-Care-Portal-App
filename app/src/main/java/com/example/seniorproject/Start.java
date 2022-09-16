package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import SignIn.DocSignIn;
import SignIn.PSignIn;
import SignUp.DocSignUp;
import SignUp.PSignUp;

public class Start extends AppCompatActivity {

    LinearLayout LinearLayout2,LinearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        LinearLayout2 =(LinearLayout)findViewById(R.id.LLP);
        LinearLayout3 =(LinearLayout)findViewById(R.id.LLD);



        LinearLayout3.setVisibility(View.INVISIBLE);
        LinearLayout2.setVisibility(View.INVISIBLE);

    }

    public void PSignIn (View V){
        Intent i = new Intent(this, PSignIn.class);
        startActivity(i);
    }

    public void PSignUp (View V){
        Intent i = new Intent(this, PSignUp.class);
        startActivity(i);
    }

    public void DSignIn (View V){
        Intent i = new Intent(this, DocSignIn.class);
        startActivity(i);
    }

    public void DSignUp (View V){
        Intent i = new Intent(this, DocSignUp.class);
        startActivity(i);
    }

    public void DoctorLL (View V){

        LinearLayout3.setVisibility(View.VISIBLE);
        LinearLayout2.setVisibility(View.INVISIBLE);
    }

    public void PatientLL (View V){
        LinearLayout2.setVisibility(View.VISIBLE);
        LinearLayout3.setVisibility(View.INVISIBLE);
    }

}
