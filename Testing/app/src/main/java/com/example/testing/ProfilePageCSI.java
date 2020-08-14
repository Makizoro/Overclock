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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ProfilePageCSI extends AppCompatActivity {

    TextView txtName;
    TextView txtType;
    TextView txtEmail;
    TextView txtDesc;
    Button btnJoin;
    Button btnForum;

    private static final String keyCSI = "csi" ;
    private static final String keyUserType = "type";
    private static final String keyUsername = "username" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_c_s_i);

        txtName = findViewById(R.id.txtName);
        txtType = findViewById(R.id.txtType);
        txtEmail = findViewById(R.id.txtEmail);
        txtDesc = findViewById(R.id.txtDescription);
        btnJoin = findViewById(R.id.btnJoin);
        btnForum = findViewById(R.id.btnCSIForum);

        if(isSubscribed() || hasRequested())
        {
            btnJoin.setEnabled(false);
            Toast.makeText(this,"Exists",Toast.LENGTH_SHORT).show();
        }else{
            btnJoin.setEnabled(true);
        }

        loadData();

        btnForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String csiName = txtName.getText().toString();
                Intent i = new Intent(ProfilePageCSI.this, CSIForum.class);
                i.putExtra("CSIName",csiName);
                startActivity(i);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Person").document(""+user.getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    String csiName = txtName.getText().toString();
                                    String username = documentSnapshot.getString("username");
                                    String userType = documentSnapshot.getString("type");

                                    Map<String, Object> data = new HashMap<>();
                                    data.put(keyUserType,userType);
                                    data.put(keyCSI,csiName);
                                    data.put(keyUsername,username);

                                    db.collection("User_Request").add(data);

                                    btnJoin.setEnabled(false);

                                    Toast.makeText(getApplicationContext(),"Your request to join "
                                            + txtName.getText().toString()+" has been sent",Toast.LENGTH_SHORT).show();


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

    public boolean isSubscribed(){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final boolean[] hasRequestedBool = {false};
        String documentId = getIntent().getStringExtra("CSIDocumentID");

        assert user != null;
        Query hasRequested = db.collection("CSI_User_Registration")
                .whereEqualTo("csiId",""+documentId)
                .whereEqualTo("userId",""+ user.getUid());



        hasRequested.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    hasRequestedBool[0] = true;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error fetching document",Toast.LENGTH_SHORT).show();
            }
        });
        return hasRequestedBool[0];
    }

    public boolean hasRequested(){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final boolean[] hasRequestedBool = {false};
        String documentId = getIntent().getStringExtra("CSIDocumentID");

        assert user != null;
        Query hasRequested = db.collection("User_Request")
                .whereEqualTo("csiId",""+documentId)
                .whereEqualTo("userId",""+ user.getUid());

        hasRequested.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    hasRequestedBool[0] = true;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error fetching document",Toast.LENGTH_SHORT).show();
            }
        });
        return hasRequestedBool[0];
    }
    public void loadData(){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        String documentId = getIntent().getStringExtra("CSIDocumentID");

        db.collection("CSI").document(""+documentId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String name = documentSnapshot.getString("name");
                            String type = documentSnapshot.getString("type");
                            String email = documentSnapshot.getString("email");
                            String desc = documentSnapshot.getString("description");

                            txtName.setText(""+name);
                            txtType.setText(""+type);
                            txtEmail.setText(""+email);
                            txtDesc.setText(""+desc);

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