package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.BloodC;
import JavaClasses.HeartC;
import MainAdapters.MainAdapterBP;
import MainAdapters.MainAdapterH;

public class RecycleH extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterH mainAdapter;

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_h);

        Name = getIntent().getStringExtra("fullName");
        recyclerView=(RecyclerView)findViewById(R.id.rv4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<HeartC> options =new FirebaseRecyclerOptions.Builder<HeartC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Heart Rate"), HeartC.class).build();

        mainAdapter = new MainAdapterH(options);
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