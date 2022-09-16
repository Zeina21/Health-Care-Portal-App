package VitalSignsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seniorproject.R;

public class VitalSigns extends AppCompatActivity {
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs);
        Name = getIntent().getStringExtra("fullName");
    }


    public void click(View v) {
        Intent i = new Intent(getApplicationContext(), weight.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }

    public void clickRR(View v) {
        Intent i = new Intent(getApplicationContext(), RespiratoryRate.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }
    public void clickBP(View v){
        Intent i = new Intent(getApplicationContext(), BloodPressure.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }

    public void clickHR(View v){
        Intent i = new Intent(getApplicationContext(), HeartRate.class);
        i.putExtra("fullName", Name);
        startActivity(i);

    }

    public void clickQA (View V){
        Intent i = new Intent(getApplicationContext(), GeneralQues.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }


}