package com.example.seniorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

import JavaClasses.ChildC;
import JavaClasses.User;
import Verification.childVerification;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddChilds extends AppCompatActivity {

    TextInputLayout Full_name, ID, age, Ref, number;
    CircleImageView I1;
    RadioButton male, female;
    RadioGroup RadioG;
    Uri imageUri;
    DatabaseReference childReference;
    String CName, Identification, Age, Gender, RefNum, Number, Name;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_childs);

        Full_name = findViewById(R.id.nameC); // Initialize variables
        ID = findViewById(R.id.idC);
        age = findViewById(R.id.ageC);
        Ref = findViewById(R.id.referenceC);
        I1 = findViewById(R.id.profileC);
        male = findViewById(R.id.rbmC);
        female = findViewById(R.id.rbfC);
        number = findViewById(R.id.phoneC);
        //RadioG = findViewById(R.id.radioB);
        fab = findViewById(R.id.floatingActionButton5);
        Name = getIntent().getStringExtra("fullName");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(i, 1);

            }
        });

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Users").child(Name).child("Childs");
        User user= new User(CName);
        reference2.push().setValue(user);
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


    public void Add(View v) {
        CName = Full_name.getEditText().getText().toString().trim();
        Identification = ID.getEditText().getText().toString().trim();
        RefNum = Ref.getEditText().getText().toString().trim();
        Age = age.getEditText().getText().toString().trim();
        Number = number.getEditText().getText().toString().trim();
        String PhoneNumber2 = "+961" + Number;

        if (male.isChecked()) {
            Gender = "Male";
        }
        if (female.isChecked()) {
            Gender = "Female";
        }


        if (CName.isEmpty()) {
            Full_name.setError("Full name is required!");
            Full_name.requestFocus();
            return;
        }

        if (Identification.isEmpty()) {
            ID.setError("ID is required!");
            ID.requestFocus();
            return;
        }
/*
        if (!male.isChecked() && !female.isChecked()) {
            Gend.setError("Gender is required");
            return;
        }
*/

        if (Age.isEmpty()) {
            age.setError("Age is required!");
            age.requestFocus();
            return;
        }


        if (RefNum.isEmpty()) {
            Ref.setError("Reference Number is required!");
            Ref.requestFocus();
            return;

        } else {





            if (imageUri != null) {
                final StorageReference filePath = FirebaseStorage.getInstance().getReference("Users").child(Name).child(CName).child("Child Profile Image");
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
                        Toast.makeText(AddChilds.this, "Image Upload failed! Try again!", Toast.LENGTH_LONG).show();
                    }
                });

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getReference() != null) {
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String Iuri = uri.toString();
                                    Map newImageMap = new HashMap();
                                    newImageMap.put("profilePictureurl", Iuri);
                                    //set url in FB
                                    childReference = FirebaseDatabase.getInstance().getReference("Users").child(CName);
                                    childReference.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(AddChilds.this, "Image url added to database!", Toast.LENGTH_SHORT).show();


                                            } else {
                                                Toast.makeText(AddChilds.this, task.getException().toString(), Toast.LENGTH_LONG).show();
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



            Intent i = new Intent(getApplicationContext(), childVerification.class);
            i.putExtra("CfullName", CName);
            i.putExtra("CID", Identification);
            i.putExtra("CAge", Age);
            i.putExtra("CGender", Gender);
            i.putExtra("CPhoneNo", PhoneNumber2);
            i.putExtra("CRefNum", RefNum);
            i.putExtra("fullName", Name);
            startActivity(i);

        }

    }
}