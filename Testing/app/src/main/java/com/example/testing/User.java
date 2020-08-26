package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class User extends AppCompatActivity {

    Button profile, csi, forum, event, notification,request ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        profile = findViewById(R.id.btnProfile);
        csi = findViewById(R.id.btnCSI);
        forum = findViewById(R.id.btnForum);
        event = findViewById(R.id.btnEvents);
        notification = findViewById(R.id.btnNotify);
        request = findViewById(R.id.btnRequest);

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(User.this,Profile.class);
                startActivity(i);
            }
        });

        csi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,TabCSILoad.class);
                startActivity(i);
            }
        });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,Forum.class);
                startActivity(i);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,Events.class);
                startActivity(i);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,LoadNotification.class);
                startActivity(i);
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,LoadCSIRequest.class);
                startActivity(i);
            }
        });

    }
}