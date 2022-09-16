package ViewLayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import VitalSignsPage.Heart;
import com.example.seniorproject.R;

public class HeartView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_view);
    }

    public void RateInfo(View V){
        Intent i = new Intent(this, Heart.class);
        startActivity(i);
    }
}