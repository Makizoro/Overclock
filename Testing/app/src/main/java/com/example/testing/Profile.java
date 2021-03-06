package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    Button update;
    TextView viewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        viewData = findViewById(R.id.txtUserType);

        update = findViewById(R.id.btnUpdateProfile);

        loadData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = getIntent().getStringExtra("def");
                Intent i = new Intent(Profile.this, ProfileUpdate.class);
                i.putExtra("def",type);
                startActivity(i);
            }
        });


    }


    public void loadData(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Person").document(""+user.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String userType = documentSnapshot.getString("type");
                            String username = documentSnapshot.getString("username");

                            viewData.setText("Type: " +userType + "\n"+"Username: " + username);
                        }else{
                            Toast.makeText(getApplicationContext(),"Document does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error fetching document",Toast.LENGTH_SHORT).show();
            }
        });
    }
}