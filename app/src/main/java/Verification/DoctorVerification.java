package Verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.seniorproject.R;
import JavaClasses.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import SignIn.DocSignIn;

public class DoctorVerification extends AppCompatActivity {


    PinView pin;
    TextView t1;
    String codeBySystem;
    String number, Name, Identification, Email, Gender;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_verification);



        pin = findViewById(R.id.pin_view2);
        mAuth = FirebaseAuth.getInstance();
        t1 = (TextView) findViewById(R.id.text1);


        number = getIntent().getStringExtra("DPhoneNo");
        Name = getIntent().getStringExtra("DocName");
        Identification = getIntent().getStringExtra("DID");
        Email = getIntent().getStringExtra("DEmail");
        Gender = getIntent().getStringExtra("DGender");


        t1.setText("Enter One Time Password Sent Onn" + number);
        sendCode(number);
    }

    private void sendCode(String number) {
        // [START start_phone_auth]
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth) //mAuth is defined on top
                .setPhoneNumber(number)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit time to take to send the msg
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pin.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(DoctorVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);


    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Verification completed successfully here Either
                            // store the data or do whatever desire
                            StoreData();
                            Toast.makeText(DoctorVerification.this, "Verification  Completed!", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(DoctorVerification.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void StoreData() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");

        User user = new User(Name, Identification, Gender, Email, number);
        reference.child(Name).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DoctorVerification.this, "Doctor has been registered Successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DoctorVerification.this, DocSignIn.class);
                    startActivity(i);
                    //  progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(DoctorVerification.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                    //  progressBar.setVisibility(View.GONE);
                }
            }
        });

    }


    public void CallDOTP(View V) {
        String code = pin.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);


        }


    }

    }