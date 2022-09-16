package VitalSignsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seniorproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.HeartC;
import RecycleView.RecycleH;

public class HeartRate extends AppCompatActivity {
    String Name;
    EditText date,time ,BPM;
    DatabaseReference HeartDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        Name = getIntent().getStringExtra("fullName");

        date=(EditText)findViewById(R.id.HRdate);
        time=(EditText)findViewById(R.id.HRtime);
        BPM=(EditText) findViewById(R.id.BPM);



        HeartDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Heart Rate");


    }

    public void SaveHH(View V){
        String Time= time.getText().toString();
        String Date = date.getText().toString();
        String HBPM =BPM.getText().toString();


       HeartC heart =new HeartC(Date,Time,HBPM);
        HeartDB.push().setValue(heart); //passing the object created above, if we don't use push method the data will be overridden
        Toast.makeText(HeartRate.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }
    public void ViewHH(View V){
        Intent i = new Intent(getApplicationContext(), RecycleH.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

    public void RateInfo(View V){
        Intent i = new Intent(this, Heart.class);
        startActivity(i);
    }
    }
