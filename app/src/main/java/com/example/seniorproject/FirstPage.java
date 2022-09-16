package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import MoreInfo.MoreInfo;
import MoreInfo.Terms;
import MoreInfo.PrivacyPolicy;

public class FirstPage extends AppCompatActivity {


    ImageButton emergency, showbottomsheet;
    String Name;
    public BottomSheetDialog bottomSheetDialog, bottomSheetDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Name = getIntent().getStringExtra("fullName");
        emergency = findViewById(R.id.Emergency);
        showbottomsheet = findViewById(R.id.showbottomsheet);

        emergency.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                bottomSheetDialog = new BottomSheetDialog(FirstPage.this, R.style.BottomSheetTheme);
                View sheetview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.buttomsheetemerg, null);

                bottomSheetDialog.setContentView(sheetview);
                bottomSheetDialog.show();

                LinearLayout redcross = bottomSheetDialog.findViewById(R.id.redcross);
                LinearLayout civil = bottomSheetDialog.findViewById(R.id.civil);
                LinearLayout hospital = bottomSheetDialog.findViewById(R.id.hosp);
                LinearLayout es3af = bottomSheetDialog.findViewById(R.id.es3af);
                LinearLayout es3af2 = bottomSheetDialog.findViewById(R.id.es3af2);

                redcross.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //Lebanese Red cross
                        String s = "tel:140";    //Red Cross number
                        Uri u = Uri.parse(s);
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);

                    }
                });

                civil.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // Civil defense
                        String s = "tel:125";    // Civil defense number
                        Uri u = Uri.parse(s);
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);
                    }

                });

                hospital.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String s = "tel:01832700";    // Emergency hospitalization number
                        Uri u = Uri.parse(s);
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);

                    }
                });


                es3af.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String s = "tel:01832700";    // الإسعاف الشعبي  number
                        Uri u = Uri.parse(s);
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);

                    }
                });

                es3af2.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String s = "tel:01832700";    // الإسعاف الرسالة number
                        Uri u = Uri.parse(s);
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);

                    }
                });


            }

        });


        showbottomsheet.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                bottomSheetDialog1 = new BottomSheetDialog(FirstPage.this, R.style.BottomSheetTheme);
                View sheetview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_buttomsheet_about, null);

                bottomSheetDialog1.setContentView(sheetview);
                bottomSheetDialog1.show();

                LinearLayout more = bottomSheetDialog1.findViewById(R.id.info);
                LinearLayout privacy = bottomSheetDialog1.findViewById(R.id.Privacy);
                LinearLayout terms = bottomSheetDialog1.findViewById(R.id.terms);


                terms.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        Intent i = new Intent(FirstPage.this, Terms.class);
                        startActivity(i);

                    }

                });


                more.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        Intent i = new Intent(FirstPage.this, MoreInfo.class);
                        startActivity(i);

                    }

                });

                privacy.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        Intent i = new Intent(FirstPage.this, PrivacyPolicy.class);
                        startActivity(i);

                    }

                });

            }
        });
    }

    public void MedRec(View V) {
        Intent i = new Intent(getApplicationContext(), Patient.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

    public void ChildRec(View V) {

        Intent i = new Intent(getApplicationContext(), SearchChild.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }


    public void Blood_Link(View V) {
        String s = "https://www.dsclebanon.org/en/#map"; // blood donation website
        Uri u = Uri.parse(s);
        Intent i = new Intent(Intent.ACTION_VIEW, u); //Open browser
        startActivity(i);


    }

    public void Others(View v) {
        Intent i = new Intent(getApplicationContext(), OthersRecord.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }
}


