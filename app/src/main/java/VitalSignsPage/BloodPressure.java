package VitalSignsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seniorproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.BloodC;
import RecycleView.RecyclePB;

public class BloodPressure extends AppCompatActivity {

    EditText BDate,Sys,Dias;
    DatabaseReference BloodDB;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        BDate=(EditText)findViewById(R.id.BPdate);
        Sys=(EditText)findViewById(R.id.PBS);
        Dias=(EditText)findViewById(R.id.BPD);
        Name = getIntent().getStringExtra("fullName");


        BloodDB= FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Blood Pressure");


    }

    public void SaveBP(View V){
        String Systolic = Sys.getText().toString();
        String Date = BDate.getText().toString();
        String Diastolic =Dias.getText().toString();


        BloodC BP =new BloodC(Systolic,Diastolic,Date);
        BloodDB.push().setValue(BP); //passing the object created above, if we don't use push methoD the data will be overridden
        Toast.makeText(BloodPressure.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }
    public void ViewBP(View V){
        Intent i = new Intent(getApplicationContext(), RecyclePB.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }


    public void bloodInfo(View V){
        Intent i = new Intent(this, Pressure.class);
        startActivity(i);
    }
}
