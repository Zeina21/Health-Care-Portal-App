package SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import Verification.DoctorVerification;
import com.example.seniorproject.R;
import com.google.android.material.textfield.TextInputLayout;

public class DocSignUp extends AppCompatActivity {
    int g;

    String Gender;
    TextInputLayout Full_name, Did, email, number;
    RadioButton male, female;
    RadioGroup RadioG;
    TextView Gend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sign_up);

        Full_name = findViewById(R.id.Docname); // Initialize variables
        Did = findViewById(R.id.docid);
        email = findViewById(R.id.Docemail);
        number = findViewById(R.id.Docnum);

        Gend = (TextView) findViewById(R.id.gender);
        male = findViewById(R.id.rbm);
        female = findViewById(R.id.rbf);
       // RadioG = findViewById(R.id.radioB);

    }

   public void SignUp(View v) {
       String Name = Full_name.getEditText().getText().toString().trim();
       String Identification = Did.getEditText().getText().toString().trim();
       String Email = email.getEditText().getText().toString().trim();
       String Number = number.getEditText().getText().toString().trim();
       String PhoneNumber2 = "+961" + Number;


       if (male.isChecked()) {
           Gender = "Male";
       }
       if (female.isChecked()) {
           Gender = "Female";
       }


       if (Name.isEmpty()) {
           Full_name.setError("Full name is required!");
           Full_name.requestFocus();
           return;
       }

       if (Identification.isEmpty()) {
           Did.setError("ID is required!");
           Did.requestFocus();
           return;
       }

       if (!male.isChecked() && !female.isChecked()) {
           Gend.setError("Gender is required");
           return;
       }


       if (Email.isEmpty()) {
           email.setError("Email is required!");
           email.requestFocus();
           return;
       }


       if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
           email.setError("Please provide valid email");
           email.requestFocus();
           return;
       }


       if (Number.isEmpty()) {
           number.setError("Number is required!");
           number.requestFocus();
           return;
       }

       if (!Patterns.PHONE.matcher(Number).matches()) {
           number.setError("Please provide a valid phone number!");
           number.requestFocus();
           return;

       } else {

           Intent i = new Intent(getApplicationContext(), DoctorVerification.class);
           i.putExtra("DocName", Name);
           i.putExtra("DID", Identification);
           i.putExtra("DEmail", Email);
           i.putExtra("DGender", Gender);
           i.putExtra("DPhoneNo", PhoneNumber2);
           startActivity(i);

       }
   }

   }