package com.example.seniorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.seniorproject.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MedicalRecord extends AppCompatActivity {

    ActivityMainBinding binding;
    StorageReference storageReference;
    String Name;
    DatabaseReference reference;
    TextView FName, ID, RNum, gender, Age, covid, Doses, Blood, height, weight, Marital, Smoking, Alcohol, Disabilities, Optical, Pain, Period, Pregnancy, DisName, DisDate, ImuName, ImuDate, OpName, OpDate;
    ImageView I2;
    Double num;
    ListView LV, LV1, LV2, LV3, LV4;
    LinearLayout L1;
    TableRow T1, T2;
    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> myArrayList1 = new ArrayList<>();
    ArrayList<String> myArrayList2 = new ArrayList<>();
    ArrayList<String> myArrayList3 = new ArrayList<>();
    ArrayList<String> myArrayList4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);


        Name = getIntent().getStringExtra("fullName");
        I2 = (ImageView) findViewById(R.id.img1);
        FName = (TextView) findViewById(R.id.Fname);
        ID = (TextView) findViewById(R.id.ID);
        RNum = (TextView) findViewById(R.id.Refnum);
        Age = (TextView) findViewById(R.id.Sage);
        gender = (TextView) findViewById(R.id.Gen);
        covid = (TextView) findViewById(R.id.vacc);
        Doses = (TextView) findViewById(R.id.dose);
        Blood = (TextView) findViewById(R.id.Q1);
        height = (TextView) findViewById(R.id.Q8);
        weight = (TextView) findViewById(R.id.Q2);
        Marital = (TextView) findViewById(R.id.Q3);
        Smoking = (TextView) findViewById(R.id.Q4);
        Alcohol = (TextView) findViewById(R.id.Q5);
        Disabilities = (TextView) findViewById(R.id.Q6);
        Optical = (TextView) findViewById(R.id.Q7);
        Pain = (TextView) findViewById(R.id.Q9);
        Period = (TextView) findViewById(R.id.Q10);
        Pregnancy = (TextView) findViewById(R.id.Q11);
        DisName = (TextView) findViewById(R.id.Q12);
        DisDate = (TextView) findViewById(R.id.Q13);
        ImuName = (TextView) findViewById(R.id.Q14);
        ImuDate = (TextView) findViewById(R.id.Q15);
        OpName = (TextView) findViewById(R.id.Q16);
        OpDate = (TextView) findViewById(R.id.Q17);
        L1 = findViewById(R.id.Back);

        T1 = (TableRow) findViewById(R.id.Table1);
        T2 = (TableRow) findViewById(R.id.Table2);


        T1.setVisibility(View.INVISIBLE);
        T2.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> myArrayAdapter1 = new ArrayAdapter<String>(MedicalRecord.this, android.R.layout.simple_list_item_1, myArrayList1);
        LV1 = (ListView) findViewById(R.id.LV1);
        LV1.setAdapter(myArrayAdapter1);


        ArrayAdapter<String> myArrayAdapter3 = new ArrayAdapter<String>(MedicalRecord.this, android.R.layout.simple_list_item_1, myArrayList3);
        LV3 = (ListView) findViewById(R.id.LV3);
        LV3.setAdapter(myArrayAdapter3);


        FName.setText(Name);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        Query query1 = reference.orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String id = String.valueOf(data.child("id").getValue());
                    ID.setText(id);

                    String PAge = String.valueOf(data.child("age").getValue());
                    Age.setText(PAge);
                    num = Double.parseDouble(Age.getText().toString());
                    if (num < 18) {
                        L1.setBackgroundResource(R.drawable.child3);
                        I2.setImageResource(R.drawable.childpic);

                    }

                    String ref = String.valueOf(data.child("refNum").getValue());
                    RNum.setText(ref);

                    String gen = String.valueOf(data.child("gender").getValue());
                    gender.setText(gen);
                    if (gen.equals("Female")) {

                        T1.setVisibility(View.VISIBLE);
                        T2.setVisibility(View.VISIBLE);
                        if (num < 18) {
                            L1.setBackgroundResource(R.drawable.child2);
                            I2.setImageResource(R.drawable.childpic2);
                        }
                    }

                    else if(gen.equals("Male")){
                        I2.setImageResource(R.drawable.menpic);
                        if(num<18){
                            I2.setImageResource(R.drawable.childpic);
                        }
                    }


                    for (DataSnapshot datachild : data.child("Measurements").getChildren()) {

                        String w = String.valueOf(datachild.child("weight").getValue());
                        weight.setText(w);

                        String H = String.valueOf(datachild.child("height").getValue());
                        height.setText(H);
                    }

                    for (DataSnapshot datachild : data.child("Health Questionnaire").getChildren()) {

                        String s = String.valueOf(datachild.child("smoking").getValue());
                        Smoking.setText(s);

                        String A = String.valueOf(datachild.child("alcohol").getValue());
                        Alcohol.setText(A);

                        String D = String.valueOf(datachild.child("disability").getValue());
                        Disabilities.setText(D);

                        String B = String.valueOf(datachild.child("blood").getValue());
                        Blood.setText(B);

                        String O = String.valueOf(datachild.child("optical").getValue());
                        Optical.setText(O);

                        String M = String.valueOf(datachild.child("marital").getValue());
                        Marital.setText(M);

                        String Pe = String.valueOf(datachild.child("period").getValue());
                        Period.setText(Pe);

                        String Pr = String.valueOf(datachild.child("pregnancy").getValue());
                        Pregnancy.setText(Pr);
                    }


                    for (DataSnapshot datachild : data.child("Covid19").getChildren()) {

                        String DosesNum = String.valueOf(datachild.child("dosesNumber").getValue());
                        Doses.setText(DosesNum);

                        if (DosesNum.equals("2") || DosesNum.equals("3") || DosesNum.equals("4")) {
                            covid.setText("Vaccinated");

                        } else if (DosesNum.equals("2")) {
                            covid.setText("Almost Vaccinated");

                        }


                    }


                    for (DataSnapshot datachild : data.child("Health Questionnaire").getChildren()) {

                        String Blood1 = String.valueOf(datachild.child("blood").getValue());
                        Blood.setText(Blood1);


                        String marital = String.valueOf(datachild.child("marital").getValue());
                        Marital.setText(marital);

                        String smoking = String.valueOf(datachild.child("smoking").getValue());
                        Smoking.setText(smoking);

                        String alcohol = String.valueOf(datachild.child("alcohol").getValue());
                        Alcohol.setText(alcohol);

                        String disability = String.valueOf(datachild.child("disability").getValue());
                        Disabilities.setText(disability);


                        String optical = String.valueOf(datachild.child("optical").getValue());
                        Optical.setText(optical);

                        String pain = String.valueOf(datachild.child("pain").getValue());
                        Pain.setText(pain);

                        String period = String.valueOf(datachild.child("period").getValue());
                        Period.setText(period);

                        String pregnancy = String.valueOf(datachild.child("pregnancy").getValue());
                        Period.setText(pregnancy);
                    }


                    for (DataSnapshot data1 : snapshot.getChildren()) {
                        for (DataSnapshot data2 : data1.child("Pathalogical States").getChildren()) {

                            String Path = String.valueOf(data2.child("dname").getValue());
                            DisName.setText(Path);

                            String DDate = String.valueOf(data2.child("sdate").getValue());
                            DisDate.setText(DDate);
                        }
                    }


                    for (DataSnapshot data1 : snapshot.getChildren()) {
                        for (DataSnapshot data2 : data1.child("Immunization").getChildren()) {

                            String Immu = String.valueOf(data2.child("name").getValue());
                            ImuName.setText(Immu);
                            String IDate = String.valueOf(data2.child("date").getValue());
                            ImuDate.setText(IDate);

                        }
                    }

                    for (DataSnapshot data1 : snapshot.getChildren()) {
                        for (DataSnapshot data2 : data1.child("Medications").getChildren()) {

                            String Med = String.valueOf(data2.child("medName").getValue());
                            myArrayList1.add(Med);
                            myArrayAdapter1.notifyDataSetChanged();


                        }
                    }

                    for (DataSnapshot data1 : snapshot.getChildren()) {
                        for (DataSnapshot data2 : data1.child("Allergies").getChildren()) {

                            String al = String.valueOf(data2.child("aname").getValue());
                            myArrayList3.add(al);
                            myArrayAdapter3.notifyDataSetChanged();

                        }
                    }
                    for (DataSnapshot data1 : snapshot.getChildren()) {
                        for (DataSnapshot data2 : data1.child("Medical History").getChildren()) {

                            String op = String.valueOf(data2.child("operation").getValue());
                            OpName.setText(op);
                            String OpD = String.valueOf(data2.child("opDate").getValue());
                            OpDate.setText(OpD);

                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}