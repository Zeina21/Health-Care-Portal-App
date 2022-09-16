package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.MedicamentC;
import JavaClasses.WeightC;

import MainAdapters.MainAdapterW;


public class RecycleW extends AppCompatActivity {


    String Name;
    RecyclerView recyclerView;
    MainAdapterW mainAdapterW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_w);


        Name = getIntent().getStringExtra("fullName");

        recyclerView = (RecyclerView) findViewById(R.id.rv8);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<WeightC> options = new FirebaseRecyclerOptions.Builder<WeightC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Measurements"), WeightC.class).build();

        mainAdapterW = new MainAdapterW(options);
        recyclerView.setAdapter(mainAdapterW);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterW.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterW.stopListening();
    }




}