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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddToCSIForum extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference forumCSIRef = db.collection("CSI_Forum");

    EditText edtTopic;
    EditText edtMessage;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_forum);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        edtTopic = findViewById(R.id.edtTopic);
        edtMessage = findViewById(R.id.edtMessage);
        btnSave = findViewById(R.id.btnSaveToForum);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = edtTopic.getText().toString();
                String message = edtMessage.getText().toString();

                final Map<String, Object> data = new HashMap<>();
                data.put("topic", topic);
                data.put("message", message);

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Person").document(""+user.getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    String username = documentSnapshot.getString("username");
                                    String csiName = getIntent().getStringExtra("CSIName");

                                    data.put("username",username);
                                    data.put("csi",csiName);

                                    forumCSIRef.add(data);

                                    String type = getIntent().getStringExtra("def");
                                    Intent i = new Intent(AddToCSIForum.this, TabCSILoad.class);
                                    i.putExtra("def",type);
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