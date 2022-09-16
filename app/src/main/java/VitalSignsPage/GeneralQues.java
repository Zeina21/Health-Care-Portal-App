package VitalSignsPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.seniorproject.Immunization;
import com.example.seniorproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import JavaClasses.ImmunizationC;
import JavaClasses.QuestionC;

public class GeneralQues extends AppCompatActivity {
    String spinnerStr;

    DatabaseReference QuestionDB,reference;
    String Name;
    String gender;
    Spinner myspinner;
    RadioButton Married, Single, NS, L10, M20, B19, ND, OA, A2, OD, OpticalRes, NOpticalRes, near, far, regular, irregular, pregnant, NPregnant, baby;
    EditText Disability, Pain, Right, Left;
    String Marital, Smoking, Alcohol, pain, Period = "Irregular", Pregnancy = "Just Had a baby", Optical;
    String disability = "Non-existent";
    CheckBox C1, C2, C3, C4;
    TableLayout Lfemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_ques);


        Name = getIntent().getStringExtra("fullName"); //get user's Name
        myspinner = (Spinner) findViewById(R.id.bloodT); //bloodT is the id of the spinner
        Married = findViewById(R.id.married);
        Single = findViewById(R.id.single);
        NS = findViewById(R.id.rb1); //non Smoker
        L10 = findViewById(R.id.rb2); // less than 10 cigarettes is selected
        M20 = findViewById(R.id.rb3); //more than 20
        B19 = findViewById(R.id.rb4);  //between 10-19 cigarettes
        ND = findViewById(R.id.rb5); //non Drinker
        OA = findViewById(R.id.rb7); //occasionally
        A2 = findViewById(R.id.rb8); //2 or more
        OD = findViewById(R.id.rb6);    //one per day
        OpticalRes = findViewById(R.id.rb10);  //No optical restriction
        NOpticalRes = findViewById(R.id.rb9);  //No optical restriction
        near = findViewById(R.id.rb11);
        far = findViewById(R.id.rb12);
        regular = findViewById(R.id.rb14); //Period irregular
        irregular = findViewById(R.id.rb15);//Period irregular
        pregnant = findViewById(R.id.rb16); //Pregnant
        NPregnant = findViewById(R.id.rb17);//Non Pregnant
        baby = findViewById(R.id.rb18);//just had a baby
        Disability = (EditText) findViewById(R.id.disability);
        C1 = findViewById(R.id.D1); // have disability
        C2 = findViewById(R.id.D2); //no disability
        C3 = findViewById(R.id.D3); //pain
        C4 = findViewById(R.id.D4); //Nopain
        Right = (EditText) findViewById(R.id.righteye);
        Left = (EditText) findViewById(R.id.lefteye);
        Lfemale = findViewById(R.id.Female);

        Lfemale.setVisibility(View.INVISIBLE);

        Right.setVisibility(View.VISIBLE);
        Left.setVisibility(View.VISIBLE);


        QuestionDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Health Questionnaire");
        reference = FirebaseDatabase.getInstance().getReference("Users");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.BloodT,
                android.R.layout.simple_spinner_item); //BloodT is the name of the array created in string.xml for vaccines names
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

        Query query1 = reference.orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                    String gen = String.valueOf(data.child("gender").getValue());
                    if (gen.equals("Female")) {
                        gender = "Female";
                        Lfemale.setVisibility(View.VISIBLE);
                    }
                    else gender="Male";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

            public void SaveQ(View v) {
                String Blood = myspinner.getSelectedItem().toString();

                if (Married.isChecked()) {
                    Marital = "Married";
                }
                if (Single.isChecked()) {
                    Marital = "Single";
                }

                if (NS.isChecked()) {
                    Smoking = " Non Smoker";
                }

                if (L10.isChecked()) {
                    Smoking = " Less than 10 per day";
                }

                if (M20.isChecked()) {
                    Smoking = " More than 20 per day";
                }

                if (B19.isChecked()) {
                    Smoking = " Between 10 and 19 per day";
                }

                if (ND.isChecked()) {
                    Alcohol = " Non Drinker";
                }

                if (OD.isChecked()) {
                    Alcohol = " One per day";
                }

                if (OA.isChecked()) {
                    Alcohol = " Occassionally";
                }

                if (A2.isChecked()) {
                    Alcohol = " Two or more per day";
                }

                if (C1.isChecked()) {
                    disability = Disability.getText().toString();
                }

                if (C3.isChecked()) {
                    pain = Pain.getText().toString();

                } else {
                    pain = "Non-existent";
                }

                if (OpticalRes.isChecked()) {


                    near.setVisibility(View.VISIBLE);
                    far.setVisibility(View.VISIBLE);

                    Right.setVisibility(View.VISIBLE);
                    Left.setVisibility(View.VISIBLE);

                    String right = Right.getText().toString();
                    String left = Left.getText().toString();
                    Optical = "Optical Restrictions Available " + right + " degree in right eye" + left + " degree in Left eye";

                }

                if (regular.isChecked()) {
                    Period = "Regular";
                }

                if (pregnant.isChecked()) {
                    Pregnancy = "Pregnant";
                }
                if (NPregnant.isChecked()) {
                    Pregnancy = "Not Pregnant";
                }


                if (gender.equals("Female")) {
                    QuestionC questionG = new QuestionC(Blood, Marital, Smoking, Alcohol, disability, pain, Optical, Period, Pregnancy);
                    QuestionDB.push().setValue(questionG);
                    Toast.makeText(GeneralQues.this, "Data inserted", Toast.LENGTH_SHORT).show();

                }

                else if (gender.equals("Male")){


                        QuestionC question = new QuestionC(Blood, Marital, Smoking, Alcohol, disability, pain, Optical);
                        QuestionDB.push().setValue(question);
                        Toast.makeText(GeneralQues.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    }





                }

            }



