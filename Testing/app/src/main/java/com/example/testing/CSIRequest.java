package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CSIRequest extends AppCompatActivity {

    Button sendRequest;
    EditText nameCSi;
    EditText descCSI;
    EditText emailCSI;
    CheckBox typeC;
    CheckBox typeS;
    CheckBox typeIg;

    private static final String keyName = "name";
    private static final String keyDesc = "description";
    private static final String keyEmail = "email";
    private static final String keyType = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_s_i_request);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        sendRequest = findViewById(R.id.btnSendRequest);
        nameCSi = findViewById(R.id.edtCSIName);
        descCSI = findViewById(R.id.edtCSIDesc);
        emailCSI = findViewById(R.id.edtCSIEmail);
        typeC = findViewById(R.id.chkClub);
        typeS = findViewById(R.id.chkSociety);
        typeIg = findViewById(R.id.chkInterestGroup);

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                String name = nameCSi.getText().toString();
                String desc = descCSI.getText().toString();
                String email = emailCSI.getText().toString();

                Map<String,Object> csiData = new HashMap<>();
                csiData.put(keyName,name);
                csiData.put(keyDesc,desc);
                csiData.put(keyEmail,email);


                if(typeC.isChecked() && !typeS.isChecked() && !typeIg.isChecked()){
                    String type = "Club";

                    csiData.put(keyType,type);

                    db.collection("CSI_Request").document(""+user.getUid()).set(csiData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"CSI request sent",Toast.LENGTH_SHORT).show();
                                    String type = getIntent().getStringExtra("def");
                                    Intent i = new Intent(CSIRequest.this, Navigation.class);
                                    i.putExtra("def",type);
                                    startActivity(i);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error saving sending CSI request",Toast.LENGTH_SHORT).show();
                        }
                    });

                }else if(!typeC.isChecked() && typeS.isChecked() && !typeIg.isChecked()){
                    String type = "Society";

                    csiData.put(keyType,type);

                    db.collection("CSI_Request").document(""+user.getUid()).set(csiData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"CSI request sent",Toast.LENGTH_SHORT).show();
                                    String type = getIntent().getStringExtra("def");
                                    Intent iS = new Intent(CSIRequest.this, Notification.class);
                                    iS.putExtra("def",type);
                                    startActivity(iS);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error saving sending CSI request",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(!typeC.isChecked()&& !typeS.isChecked() && typeIg.isChecked()){
                    String type = "Interest Group";

                    csiData.put(keyType,type);

                    db.collection("CSI_Request").document(""+user.getUid()).set(csiData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"CSI request sent",Toast.LENGTH_SHORT).show();
                                    Intent iIG = new Intent(CSIRequest.this, Navigation.class);
                                    String type = getIntent().getStringExtra("def");
                                    iIG.putExtra("def",type);
                                    startActivity(iIG);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error saving sending CSI request",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Please select one CSI",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}