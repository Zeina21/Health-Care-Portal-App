package MoreInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seniorproject.FirstPage;
import com.example.seniorproject.R;
import com.example.seniorproject.buttomsheetAbout;

public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


    }


    public void Back(View v){

        Intent i = new Intent(this, FirstPage.class);
        startActivity(i);
    }


}