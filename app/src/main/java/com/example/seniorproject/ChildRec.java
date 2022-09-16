package com.example.seniorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class ChildRec extends AppCompatActivity {
    String Name, CName;
    DatabaseReference reference;
    ListView LV;
    ArrayList<String> myArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_rec);


        Name = getIntent().getStringExtra("fullName");
        CName = getIntent().getStringExtra("ChildName");



        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(ChildRec.this, android.R.layout.simple_list_item_1, myArrayList);
        LV = (ListView) findViewById(R.id.LV5);
        LV.setAdapter(myArrayAdapter);
        reference = FirebaseDatabase.getInstance().getReference("Users");

        Query query1 = reference.orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                        for (DataSnapshot data2 : data.child("Childs").getChildren()) {


                            String Childs = String.valueOf(data2.child("name").getValue());
                            myArrayList.add(Childs);
                            myArrayAdapter.notifyDataSetChanged();
                            Log.i("Done", Childs);


                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




    }


    public void AddChild(View v) {

        Intent i = new Intent(getApplicationContext(), AddChilds.class);
        i.putExtra("fullName", Name);

        startActivity(i);

    }

    public void SearchC(View v) {
        Intent i = new Intent(getApplicationContext(), SearchChild.class);
        i.putExtra("fullName", Name);

        startActivity(i);

    }

}