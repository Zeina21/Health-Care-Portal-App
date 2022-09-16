package SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seniorproject.R;
import Verification.VerificationCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PSignUp extends AppCompatActivity {


    String Gender;
    TextInputLayout Full_name, id, email, number, age, Ref;
    RadioButton male, female;
    RadioGroup RadioG;
    TextView Gend;
    private ProgressBar progressBar;
    Uri imageUri;
    CircleImageView I1;
    DatabaseReference userReference;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psign_up);


        Full_name = findViewById(R.id.name); // Initialize variables
        id = findViewById(R.id.id);
        email = findViewById(R.id.email);
        number = findViewById(R.id.num);
        age = findViewById(R.id.age);
        Ref = findViewById(R.id.reference);
        I1 = findViewById(R.id.profile);
        //Gend = (TextView) findViewById(R.id.gender);
        fab = findViewById(R.id.floatingActionButton4);
        male = findViewById(R.id.rbm);
        female = findViewById(R.id.rbf);
       // RadioG = findViewById(R.id.radioB);


Ref.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(PSignUp.this, "Add a reference number from your choice to let others accessing your medical summary", Toast.LENGTH_LONG).show();
    }
});


        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//get Image from gallery
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(i, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            {

                imageUri = data.getData();
                I1.setImageURI(imageUri);
            }

        }
    }

    public void SignUp(View v) {

        String Name = Full_name.getEditText().getText().toString().trim();
        String Identification = id.getEditText().getText().toString().trim();
        String Email = email.getEditText().getText().toString().trim();
        String ReferenceNum = Ref.getEditText().getText().toString().trim();
        String Age = age.getEditText().getText().toString().trim();
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
            id.setError("ID is required!");
            id.requestFocus();
            return;
        }
/*
        if (!male.isChecked() && !female.isChecked()) {
            Gend.setError("Gender is required");
            return;
        }*/


        if (Email.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
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
        }


        if (Age.isEmpty()) {
            age.setError("Age is required!");
            age.requestFocus();
            return;
        }


        Double num = Double.parseDouble(Age);

       if (num < 18) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return;
        }



        if (ReferenceNum.isEmpty()) {
            Ref.setError("Reference Number is required!");
            Ref.requestFocus();
            return;

        } else {


            if(imageUri !=null) {
                final StorageReference filePath = FirebaseStorage.getInstance().getReference("Users").child("Profile Image").child(Name);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), imageUri);

                } catch (IOException e) {
                    e.printStackTrace();
                }//get  image picked from gallery, compress it and upload it to FB
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                byte[] bitdata = byteArrayOutputStream.toByteArray();
                UploadTask uploadTask = filePath.putBytes(bitdata);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PSignUp.this, "Image Upload failed! Try again!", Toast.LENGTH_LONG).show();
                    }
                });

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if(taskSnapshot.getMetadata() !=null && taskSnapshot.getMetadata().getReference() !=null){
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String Iuri = uri.toString();
                                    Map newImageMap = new HashMap();
                                    newImageMap.put("profilePictureurl", Iuri);
                                    //set url in FB
                                    userReference= FirebaseDatabase.getInstance().getReference("Users").child(Name);
                                    userReference.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(PSignUp.this, "Image url added to database!", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(PSignUp.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });


                                }
                            });
                            finish();
                        }
                    }
                });
            }


            Intent i = new Intent(getApplicationContext(), VerificationCode.class);
            i.putExtra("fullName", Name);
            i.putExtra("ID", Identification);
            i.putExtra("Email", Email);
            i.putExtra("Age", Age);
            i.putExtra("Gender", Gender);
            i.putExtra("PhoneNo", PhoneNumber2);
            i.putExtra("RefNum", ReferenceNum);
            startActivity(i);
        }
    }


}