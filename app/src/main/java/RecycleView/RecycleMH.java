package RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import JavaClasses.HistoryC;
import MainAdapters.MainAdapterMH;

public class RecycleMH extends AppCompatActivity {
String Name;
    MainAdapterMH mainAdapterMH;
    RecyclerView recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_mh);
        Name = getIntent().getStringExtra("fullName");
        recyclerView2 = (RecyclerView) findViewById(R.id.rv6);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<HistoryC> options = new FirebaseRecyclerOptions.Builder<HistoryC>().setQuery(FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Medical History"), HistoryC.class).build();

        mainAdapterMH= new MainAdapterMH(options);
        recyclerView2.setAdapter(mainAdapterMH);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterMH.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterMH.stopListening();
    }
}