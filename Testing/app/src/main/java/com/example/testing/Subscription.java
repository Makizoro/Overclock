package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Subscription extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference csiRef = db.collection("Person");

    private CSISubsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        setRecycler();

    }

    private void setRecycler() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = csiRef.document("" +user.getUid())
                .collection("Subscriptions");

        FirestoreRecyclerOptions<CSISubs> options = new FirestoreRecyclerOptions.Builder<CSISubs>()
                .setQuery(query, CSISubs.class)
                .build();

        adapter = new CSISubsAdapter(options);

        RecyclerView rv = findViewById(R.id.rcyViewLoadSubs);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);

    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String type = getIntent().getStringExtra("def");
        Intent i = new Intent(Subscription.this, Navigation.class);
        i.putExtra("def",type);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}