package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.MedicamentC;
import MainAdapters.MainAdapterM;

public class RecycleMed extends AppCompatActivity {
    String Name;
    RecyclerView recyclerView;
  MainAdapterM mainAdapterM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_med);

        Name = getIntent().getStringExtra("fullName");

        recyclerView = (RecyclerView) findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MedicamentC> options = new FirebaseRecyclerOptions.Builder<MedicamentC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Medications"), MedicamentC.class).build();

        mainAdapterM = new MainAdapterM(options);
        recyclerView.setAdapter(mainAdapterM);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterM.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterM.stopListening();
    }

}