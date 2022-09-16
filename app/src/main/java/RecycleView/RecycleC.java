package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.CovidC;
import MainAdapters.MainAdapterC;

public class RecycleC extends AppCompatActivity {
    String Name;
   MainAdapterC mainAdapterC;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_c);

        Name = getIntent().getStringExtra("fullName");
        recyclerView2 = (RecyclerView) findViewById(R.id.rv2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CovidC> options = new FirebaseRecyclerOptions.Builder<CovidC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Covid19"), CovidC.class).build();

        mainAdapterC = new MainAdapterC(options);
        recyclerView2.setAdapter(mainAdapterC);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterC.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterC.stopListening();
    }
}
