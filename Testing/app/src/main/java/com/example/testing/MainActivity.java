package com.example.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int myRequestCode = 13;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
               new AuthUI.IdpConfig.AnonymousBuilder().build() );

        showSignInOptions();

    }

    private void showSignInOptions(){
        AuthMethodPickerLayout custom = new AuthMethodPickerLayout
                .Builder(R.layout.load_screen)
                .setEmailButtonId(R.id.btnLSEmail)
                .setAnonymousButtonId(R.id.btnLSAnon)
                .build();

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                        .setAuthMethodPickerLayout(custom).setTheme(R.style.LoginTheme)
                .build(), myRequestCode
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == myRequestCode){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                final DocumentReference userRef = db.collection("Person").document(""+user.getUid());

                Toast.makeText(this,""+user.getEmail(),Toast.LENGTH_SHORT).show();
                int timeout = 4000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        userRef.get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            Intent i =  new Intent(MainActivity.this,Navigation.class);
                                            i.putExtra("def",""+documentSnapshot.get("type"));
                                            startActivity(i);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Set your username",Toast.LENGTH_SHORT).show();
                                            Intent i =  new Intent(MainActivity.this,Username.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }, timeout);
            }else {
                Toast.makeText(this,""+response.getError().getMessage(),Toast.LENGTH_SHORT).show();

            }

        }
    }
}