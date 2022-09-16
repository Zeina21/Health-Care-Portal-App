package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.HeartC;
import JavaClasses.RespiratoryC;
import MainAdapters.MainAdapterH;
import MainAdapters.MainAdapterRR;

public class RecycleRR extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterRR mainAdapter;

    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_rr);


        Name = getIntent().getStringExtra("fullName");
        recyclerView=(RecyclerView)findViewById(R.id.rv7);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<RespiratoryC> options =new FirebaseRecyclerOptions.Builder<RespiratoryC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Respiratory Rate"), RespiratoryC.class).build();

        mainAdapter = new MainAdapterRR(options);
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