package com.example.seniorproject;

import static android.graphics.drawable.Drawable.createFromPath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import CovidInfo.CovidInfo;

public class Covid19 extends AppCompatActivity {
    String Name;
    DatabaseReference reference;
    TextView InfectionDate, VaccType, NumDoses, DoseDate;
    Double num1;
    LinearLayout L1;
    ImageView I1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);




        Name = getIntent().getStringExtra("fullName");

        I1 = (ImageView) findViewById(R.id.Im1);
L1=(LinearLayout)findViewById(R.id.CovidLL);
        InfectionDate = (TextView) findViewById(R.id.DateIn);
        VaccType = (TextView) findViewById(R.id.VaccT);
        NumDoses = (TextView) findViewById(R.id.NumD);
        DoseDate = (TextView) findViewById(R.id.DoseD);


        reference = FirebaseDatabase.getInstance().getReference("Users");
        L1.setBackgroundResource(R.drawable.redback);
        I1.setImageResource(R.drawable.nvacc);


        Query query1 = reference.orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot data : snapshot.getChildren()) {
                    for (DataSnapshot datachild : data.child("Covid19").getChildren()) {

                        String V = String.valueOf(datachild.child("type").getValue());
                        VaccType.setText(V);

                        String id = String.valueOf(datachild.child("infection").getValue());
                        InfectionDate.setText(id);

                        String DD = String.valueOf(datachild.child("vacc").getValue());
                        DoseDate.setText(DD);


                        String ND = String.valueOf(datachild.child("dosesNumber").getValue());
                        NumDoses.setText(ND);
                        num1 = Double.parseDouble(NumDoses.getText().toString());
                        if (num1 == 2|| num1==1) {
                            L1.setBackgroundResource(R.drawable.grayback);
                            I1.setImageResource(R.drawable.avacc);

                        } else if (num1 == 3 || num1 == 4) {
                            L1.setBackgroundResource(R.drawable.greenback);
                            I1.setImageResource(R.drawable.vaccinated);
                        }

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void CovidInfo(View V){
        Intent i = new Intent(this, CovidInfo.class);
        startActivity(i);

    }
}