package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddToForum extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference forumRef = db.collection("Forum");

    EditText edtTopic;
    EditText edtMessage;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_forum);

        edtTopic = findViewById(R.id.edtTopic);
        edtMessage = findViewById(R.id.edtMessage);
        btnSave = findViewById(R.id.btnSaveToForum);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = edtTopic.getText().toString();
                String message = edtMessage.getText().toString();

                final Map<String, Object> data = new HashMap<>();
                data.put("topic",topic);
                data.put("message",message);

                Intent i = new Intent(AddToForum.this,Forum.class);
                startActivity(i);

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Person").document(""+user.getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    String username = documentSnapshot.getString("username");

                                    data.put("username",username);

                                    forumRef.add(data);

                                    Intent i = new Intent(AddToForum.this, Forum.class);
                                    startActivity(i);

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
        });
    }
}