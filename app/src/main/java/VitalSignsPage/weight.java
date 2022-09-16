package VitalSignsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ViewLayouts.MeasurementView;
import com.example.seniorproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.WeightC;

public class weight extends AppCompatActivity {
    EditText et1,et2,et3;
    DatabaseReference BodyDB;
    TextView bmi,Status;
    Double calculate,c2,c1;
    String Name,result,Weight,Height,status,BMIstatus,BMI,DateW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        et1=(EditText)findViewById(R.id.height);
        et2=(EditText)findViewById(R.id.Weight);
        et3=(EditText)findViewById(R.id.dateW);
        bmi=(TextView)findViewById(R.id.BMI);
        Status=(TextView)findViewById(R.id.status);
        Name = getIntent().getStringExtra("fullName"); //get user's Name




       BodyDB= FirebaseDatabase.getInstance().getReference().child("Users").child(Name).child("Measurements");


    }


public void ViewMeas(View V){

    Intent i = new Intent(getApplicationContext(), MeasurementView.class);
    i.putExtra("fullName", Name);

    startActivity(i);
}



    public void SaveMeas(View V){


        Weight = et2.getText().toString();
        Height = et1.getText().toString();
        DateW =et3.getText().toString();
        BMI= bmi.getText().toString();
        status=Status.getText().toString();

        WeightC weight =new WeightC(Weight,Height,BMI,status,DateW);
       BodyDB.push().setValue(weight); //passing the object created above, if we don't use push methos the data will be overridden
        Toast.makeText(weight.this,"Data inserted",Toast.LENGTH_SHORT).show();




    }

    public void CalculateBMI(View V){
        c1=Double.parseDouble(et1.getText().toString());
        c2=Double.parseDouble(et2.getText().toString());
        c1=c1/100;
        calculate=c2/(c1*c1);
        result=String.format("%2f",calculate);


        if(calculate<16)
            BMIstatus = "Severly under weight!";
        else if(calculate<18.5)
            BMIstatus = " Under weight!";
        else if(calculate>=18.5 && calculate<=24.9)
            BMIstatus = " Normal weight :)";
        else if(calculate>=25 && calculate<=29.9)
            BMIstatus = " Overweight ";
        else
            BMIstatus="Obese";


        bmi.setText(result);
        Status.setText(BMIstatus);
    }
}