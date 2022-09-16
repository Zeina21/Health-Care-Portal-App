package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.AllergiesC;
import RecycleView.RecycleA;
import RecycleView.RecycleRR;

public class Allergies extends AppCompatActivity {
    String spinnerStr;
    Spinner myspinner;
    String Name, DocName;
    String Type, AName;
    DatabaseReference AllergiesDB;
    EditText others, SName, Date;
    RadioButton RB1, RB2;

    //  LinearLayout Linear4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);

        RB1 = findViewById(R.id.rbA);
        RB2 = findViewById(R.id.rbS);
        others = (EditText) findViewById(R.id.other2);
        SName = (EditText) findViewById(R.id.SideName);
        Date = (EditText) findViewById(R.id.ADate);
        Name = getIntent().getStringExtra("fullName");
        DocName = getIntent().getStringExtra("DocName");


        AllergiesDB = FirebaseDatabase.getInstance().getReference().child(Name).child("Allergies");
        // Linear4 =(LinearLayout)findViewById(R.id.LL4);
        myspinner = (Spinner) findViewById(R.id.sp1); //sp1 is the id of the spinner


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Allergies,
                android.R.layout.simple_spinner_item); //Allergies is the name of the array created in string.xml for vaccines names
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                                    spinnerStr = parent.getItemAtPosition(pos).toString();
                                                }

                                                public void onNothingSelected(AdapterView<?> parent) {
                                                    spinnerStr = "nothing selected";
                                                }
                                            }
        );

        // Linear4.setVisibility(View.INVISIBLE);

    }

    public void SaveAllergies(View V) {

        if (RB1.isChecked()) {
            Type = "Allergie";


            String AllergiesName = myspinner.getSelectedItem().toString();
            if (AllergiesName.equals("Others"))
                AName = others.getText().toString();

            else
                AName = AllergiesName;
        }

        if (RB2.isChecked()) {
            Type = "Side Effect";
            AName = SName.getText().toString();
        } else {
            String date = Date.getText().toString();

            AllergiesDB = FirebaseDatabase.getInstance().getReference().child("Users").child(Name).child("Allergies");
            AllergiesC allergie = new AllergiesC(Type, AName, date, DocName);
            AllergiesDB.push().setValue(allergie); //passing the object created above, if we don't use push methos the data will be overridden
            Toast.makeText(Allergies.this, "Data inserted", Toast.LENGTH_SHORT).show();

        }

    }


    public void ViewA(View V){
        Intent i = new Intent(getApplicationContext(), RecycleA.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

    }