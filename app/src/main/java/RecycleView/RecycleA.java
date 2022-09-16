package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.AllergiesC;
import JavaClasses.CovidC;
import MainAdapters.MainAdapterA;
import MainAdapters.MainAdapterC;

public class RecycleA extends AppCompatActivity {

    String Name;
    MainAdapterA mainAdapterA;
    RecyclerView recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);



        Name = getIntent().getStringExtra("fullName");
        recyclerView2 = (RecyclerView) findViewById(R.id.rv5);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AllergiesC> options = new FirebaseRecyclerOptions.Builder<AllergiesC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Allergies"), AllergiesC.class).build();

        mainAdapterA = new MainAdapterA(options);
        recyclerView2.setAdapter(mainAdapterA);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterA.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterA.stopListening();
    }
}
