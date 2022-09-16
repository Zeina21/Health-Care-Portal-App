package ViewLayouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seniorproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import RecycleView.Path2;
import RecycleView.RecycleW;

public class MeasurementView extends AppCompatActivity {

    String Name;
    DatabaseReference reference;
    TextView weight,height,bmi,date;
    ImageView I3;
    Double calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_view);

        height=(TextView)findViewById(R.id.Height);
        weight=(TextView)findViewById(R.id.WeightM);
        bmi=(TextView)findViewById(R.id.bmiM);
       date=(TextView)findViewById(R.id.DateM);
        I3=(ImageView)findViewById(R.id.Im3);

        Name = getIntent().getStringExtra("fullName");
        reference = FirebaseDatabase.getInstance().getReference("Users");


        Query query1 = reference.orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot data : snapshot.getChildren()) {
                    for (DataSnapshot datachild : data.child("Measurements").getChildren()) {

                        String w = String.valueOf(datachild.child("weight").getValue());
                        weight.setText(w);

                        String H = String.valueOf(datachild.child("height").getValue());
                        height.setText(H);

                        String BMI = String.valueOf(datachild.child("bmi").getValue());
                        bmi.setText(BMI);


                        calculate=Double.parseDouble(bmi.getText().toString());


                        if(calculate<16)
                            I3.setImageResource(R.drawable.severe);
                        else if(calculate<18.5)
                            I3.setImageResource(R.drawable.under);
                        else if(calculate>=18.5 && calculate<=24.9)
                            I3.setImageResource(R.drawable.norma);
                        else if(calculate>=25 && calculate<=29.9)
                            I3.setImageResource(R.drawable.over);
                        else
                            I3.setImageResource(R.drawable.obese);



                        String Date = String.valueOf(datachild.child("wdate").getValue());
                       date.setText(Date);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void Track(View v){
        Intent i = new Intent(getApplicationContext(), RecycleW.class);
        i.putExtra("fullName", Name);
        startActivity(i);
    }

}