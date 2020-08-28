package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileUpdate extends AppCompatActivity {

    Button updateUsername, subs;
    EditText newUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        updateUsername = findViewById(R.id.btnUpdateUsername);
        newUsername = findViewById(R.id.edtNewUsername);
        subs = findViewById(R.id.btnSubscription);

        subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = getIntent().getStringExtra("def");
                Intent i = new Intent(ProfileUpdate.this, Subscription.class);
                i.putExtra("def",type);
                startActivity(i);
            }
        });

        updateUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                String updateUsername = newUsername.getText().toString();

                db.collection("Person").document(""+user.getUid()).update("username",updateUsername);

                Toast.makeText(getApplicationContext(),"Username updated",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ProfileUpdate.this, User.class);
                startActivity(i);
            }
        });
    }
}