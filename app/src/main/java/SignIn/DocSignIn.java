package SignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.seniorproject.R;
import com.example.seniorproject.SearchPatient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DocSignIn extends AppCompatActivity {
    String Name;
    TextInputLayout id, phonenumb, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sign_in);

        phonenumb = findViewById(R.id.Dnum);
        id = findViewById(R.id.Did);
        name = findViewById(R.id.nameD);
        //Name = getIntent().getStringExtra("fullName");


    }



    public void DSignIn(View V) {


            if (!validateFields()) {
                return;
            }
            //get data
            String Identification = id.getEditText().getText().toString().trim();//use trim to remove extra spaces at the end
            String Name = name.getEditText().getText().toString().trim();
            String Number = phonenumb.getEditText().getText().toString().trim();
            String PhoneNumber2 = "+961" + Number;

            //Firebase query, getInstance is the root Node, ordering it by the phone number key Node
            Query checkUser = FirebaseDatabase.getInstance().getReference("Doctors").orderByChild("num").equalTo(PhoneNumber2);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) { // if data exist/received, no error with phonenum
                        phonenumb.setError(null);
                        phonenumb.setErrorEnabled(false);

                        String ID = snapshot.child(Name).child("did").getValue(String.class);
                        if (ID.equals(Identification)) {
                            id.setError(null);
                            id.setErrorEnabled(false);

                            Intent i = new Intent(getApplicationContext(), SearchPatient.class);
                            i.putExtra("DocName", Name);
                            startActivity(i);

                        } else {
                            Toast.makeText(DocSignIn.this, "ID does not match!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(DocSignIn.this, "No such user exist!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DocSignIn.this, error.getMessage(), Toast.LENGTH_LONG).show(); //show the error with a toast notification
                }
            });
        }

        private boolean validateFields() {

            //First take the values of the user input and convert them to a string so we can do some validation on them

            String Identification = id.getEditText().getText().toString().trim();//use trim to remove extra spaces at the end
            String Number = phonenumb.getEditText().getText().toString().trim();


            //Now Doing some validations to check that the user has not provided an empty credentials
            if (Identification.isEmpty()) {
                id.setError("ID is required1!");
                id.requestFocus(); //Focus on ID input
                return false;
            } else if (Number.isEmpty()) {
                phonenumb.setError("Password is required!");
                phonenumb.requestFocus(); //Focus on Password input
                return false;

            } else
                return true;
        }
    }
