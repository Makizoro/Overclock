package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Username extends AppCompatActivity {

    Button saveUsername;
    EditText username;
    private static final String keyType = "type" ;
    private static final String keyUsername = "username";
    private Map<String, Object> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        username = findViewById(R.id.edtUsername);
        saveUsername = findViewById(R.id.btnSaveUsername);

        saveUsername();
    }

    public void saveUsername(){

        saveUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String userType = "User";
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                userData = new HashMap<>();
                userData.put(keyType,userType);
                userData.put(keyUsername,name);

                db.collection("Person").document(""+user.getUid()).set(userData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Username Saved",Toast.LENGTH_SHORT).show();
                                Intent i =  new Intent(Username.this,Navigation.class);
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error saving username",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}