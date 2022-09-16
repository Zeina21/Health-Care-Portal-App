package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.ImmunizationC;
import MainAdapters.MainAdapter;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Name = getIntent().getStringExtra("fullName");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<ImmunizationC> options =new FirebaseRecyclerOptions.Builder<ImmunizationC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Immunization"), ImmunizationC.class).build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();

    }
}