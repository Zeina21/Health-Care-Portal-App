package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.BloodC;
import MainAdapters.MainAdapterBP;

public class RecyclePB extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterBP mainAdapter;

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_pb);

        Name = getIntent().getStringExtra("fullName");
        recyclerView=(RecyclerView)findViewById(R.id.rv3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<BloodC> options =new FirebaseRecyclerOptions.Builder<BloodC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Blood Pressure"), BloodC.class).build();

        mainAdapter = new MainAdapterBP(options);
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