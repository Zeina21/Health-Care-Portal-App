package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.CovidC;

public class Covid extends AppCompatActivity {
    String spinnerStr;
    String DoseNum="0";
    EditText etdate,Vdate;
    String InfecDate="";
    String CovidDate="";
    String Name;
    Spinner myspinner;
    private final int CHOOSE_PDF_FROM_DEVICE = 1001;
    private static final String TAG = "Covid";
    private Button InsertPDF;
    LinearLayout LinearLayout2;
    DatabaseReference CovidDB;
    CheckBox C1,C2,C3,C4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);
        LinearLayout2 =(LinearLayout)findViewById(R.id.LL2);// if infected



        C1=findViewById(R.id.D1); //dose1 selected
        C2=findViewById(R.id.D2); //dose2 selected
        C3=findViewById(R.id.D3); //dose3 selected
        C4=findViewById(R.id.D4); //dose4 selected

        Name = getIntent().getStringExtra("fullName"); //get user's Name

        myspinner = (Spinner) findViewById(R.id.sp); //sp is the id of the spinner

        etdate=(EditText)findViewById(R.id.InfecDate); //infection Date
        Vdate=(EditText)findViewById(R.id.CovidDate); //Vaccine Date

        CovidDB= FirebaseDatabase.getInstance().getReference().child("Users").child(Name).child("Covid19");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Vaccines,
                android.R.layout.simple_spinner_item); //Vaccines is the name of the array created in string.xml for vaccines names
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                            {
                                                public void onItemSelected(AdapterView <?> parent, View view,int pos,long id){
                                                    spinnerStr = parent.getItemAtPosition(pos).toString();
                                                }

                                                public void onNothingSelected (AdapterView < ? > parent){
                                                    spinnerStr = "nothing selected";
                                                }
                                            }
        );



      //  LinearLayout3.setVisibility(View.INVISIBLE);
        LinearLayout2.setVisibility(View.INVISIBLE); // if infected

        InsertPDF = findViewById(R.id.PDF);

        InsertPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertPDF();
            }
        });


    }

    private void InsertPDF() {
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

            }
        }
    }

    public void SaveCovid(View V){

         InfecDate=etdate.getText().toString();
         CovidDate=Vdate.getText().toString();

        String Type= myspinner.getSelectedItem().toString();

        if(C1.isChecked()){
        DoseNum=" 1 ";
        }
        if(C2.isChecked()){
            DoseNum="2";
        }
        if(C3.isChecked()){
            DoseNum="3";
        }
        if(C4.isChecked()){
            DoseNum="4";
        }

        CovidC Covid19 =new CovidC(InfecDate,Type,DoseNum,CovidDate);
        CovidDB.push().setValue(Covid19); //passing the object created above, if we don't use push method the data will be overridden
        Toast.makeText(Covid.this,"Data inserted",Toast.LENGTH_SHORT).show();
    }



    public void YESC(View v){
    LinearLayout2.setVisibility(View.VISIBLE);
    }


    public void NOC(View v) {
        InfecDate= "N/A";
    }

    public void YESV(View v){
      //  LinearLayout3.setVisibility(View.VISIBLE);

    }

    public void NOV(View v){
        DoseNum="0";
        CovidDate="N/A";
    }

    public void ViewCovid(View V){
        Intent i = new Intent(getApplicationContext(), Covid19.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }
}