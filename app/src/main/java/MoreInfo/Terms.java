package MoreInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seniorproject.FirstPage;
import com.example.seniorproject.R;

public class Terms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
    }

    public void Back(View v){

        Intent i = new Intent(this, FirstPage.class);
        startActivity(i);
    }
}