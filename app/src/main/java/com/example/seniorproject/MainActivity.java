package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    private FirebaseAuth Senior_Project;
    private static int SCREEN_TIMER = 6000; //First slide will appear for 6 seconds
    TextView slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation( this, R.anim.bottom_animation);

//Hooks

        slogan= findViewById(R.id.slogan);


        slogan.setAnimation(bottomAnim);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent intent = new Intent(MainActivity.this,Start.class);
                    startActivity(intent);
                    finish();
                }
            },SCREEN_TIMER);

        }






    }

/*

    public void SignUp (View V){
        Intent i = new Intent(this,PSignUp.class);
        startActivity(i);
    }

    public void SignIn (View V){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }*/







