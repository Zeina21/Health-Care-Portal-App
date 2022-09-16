package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.PathC;
import RecycleView.Path2;

public class PathalogicalState extends AppCompatActivity {
    String spinnerStr, UserID;

    String PathName = "";
    EditText date, treatment, Others;
    Spinner myspinner;
    Button Button1;
    LinearLayout L1;
    DatabaseReference PathDB;
    String PName,DocName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathalogical_state);
        L1 = (LinearLayout) findViewById(R.id.LL1);
        myspinner = (Spinner) findViewById(R.id.sp2); //sp2 is the id of the spinner
        date = (EditText) findViewById(R.id.DPath);
        treatment = (EditText) findViewById(R.id.TPath);
        Others = (EditText) findViewById(R.id.other);

        PName = getIntent().getStringExtra("fullName");
        DocName = getIntent().getStringExtra("DocName");

        PathDB = FirebaseDatabase.getInstance().getReference().child("Users").child(PName).child("Pathalogical States");
        //Get The logged in User ID
        //UserID= user.getUid();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Diseases,
                android.R.layout.simple_spinner_item); //Diseases is the name of the array created in string.xml for vaccines names
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

        L1.setVisibility(View.INVISIBLE);


    }




    public void NOD(View v) {

        L1.setVisibility(View.INVISIBLE);
        PathName = "Does not suffer from any chronic conditions";
    }


    public void ViewPath(View V) {
        Intent i = new Intent(getApplicationContext(), Path2.class);
        i.putExtra("fullName", PName);
        startActivity(i);

    }

    public void SavePath(View V) {
        String Name = myspinner.getSelectedItem().toString();
        if (Name.equals("Others"))
            PathName = Others.getText().toString();
        else
            PathName = Name;

        String Date = date.getText().toString();
        String Treatment = treatment.getText().toString();
        PathC Pathalogical = new PathC( PathName, Date, Treatment,DocName);
        PathDB.push().setValue(Pathalogical);
        Toast.makeText(PathalogicalState.this, "Data inserted", Toast.LENGTH_SHORT).show();

    }
}