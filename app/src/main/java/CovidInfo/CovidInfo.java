package CovidInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.seniorproject.Centers;
import com.example.seniorproject.R;

public class CovidInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_info);
    }


    public void CovidCenters(View v){
        Intent i = new Intent(this, Centers.class);
        startActivity(i);


    }

    public void CovidSymptoms(View V){
        Intent i = new Intent(this, Symptoms.class);
        startActivity(i);
    }

    public void PreInf(View v){
        Intent i = new Intent(this, PreInfection.class);
        startActivity(i);
    }

    public void Protection(View v){
        Intent i = new Intent(this, Protection.class);
        startActivity(i);
    }

    public void PostInfection(View v){
        Intent i = new Intent(this, PostInfection.class);
        startActivity(i);
    }

    public void CovidNum(View v){
        String s = "tel:1214";    //Covid19 number
        Uri u = Uri.parse(s);
        Intent i = new Intent(Intent.ACTION_DIAL, u);
        startActivity(i);
    }



}