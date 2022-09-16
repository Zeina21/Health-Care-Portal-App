package VitalSignsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seniorproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.HeartC;
import JavaClasses.RespiratoryC;
import RecycleView.RecycleH;
import RecycleView.RecycleRR;

public class RespiratoryRate extends AppCompatActivity {

    EditText RRTime,RRDate,breath;

    DatabaseReference RespiratoryDB;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratory_rate);


        Name = getIntent().getStringExtra("fullName");

        RRTime=(EditText)findViewById(R.id.Rtime);
        RRDate=(EditText)findViewById(R.id.Rdate);
        breath=(EditText)findViewById(R.id.Rbreath);




        RespiratoryDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Respiratory Rate");


    }

    public void SaveRR(View V){
        String Time= RRTime.getText().toString();
        String Date = RRDate.getText().toString();
        String Breath =breath.getText().toString();


        RespiratoryC respiratory =new RespiratoryC(Date,Time,Breath);
        RespiratoryDB.push().setValue(respiratory); //passing the object created above, if we don't use push method the data will be overridden
        Toast.makeText(RespiratoryRate.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }
    public void ViewRR(View V){
        Intent i = new Intent(getApplicationContext(), RecycleRR.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

    public void RespInfo(View V){
        Intent i = new Intent(this,Respiratory.class);
        startActivity(i);
    }
}