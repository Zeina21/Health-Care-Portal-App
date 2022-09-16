package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import JavaClasses.PathC;
import MainAdapters.MainAdapterP;

public class Path2 extends AppCompatActivity {
    String Name;
    RecyclerView recyclerView;
    MainAdapterP mainAdapterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path2);
        Name = getIntent().getStringExtra("fullName");

        recyclerView = (RecyclerView) findViewById(R.id.rv3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PathC> options = new FirebaseRecyclerOptions.Builder<PathC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Pathalogical States"), PathC.class).build();

        mainAdapterP = new MainAdapterP(options);
        recyclerView.setAdapter(mainAdapterP);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterP.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterP.stopListening();
    }
}
