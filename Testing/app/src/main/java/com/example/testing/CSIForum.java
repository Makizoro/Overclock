package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CSIForum extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference forumCSIRef = db.collection("CSI_Forum");

    private ForumCSIAdapter adapter;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_s_i_forum);
        getWindow().getAttributes().windowAnimations = R.style.slide;

        fab = findViewById(R.id.fabAddCSIForumMessage);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CSIForum.this,AddToCSIForum.class);
                String csiName = getIntent().getStringExtra("CSIName");
                i.putExtra("CSIName",csiName);
                startActivity(i);
            }
        });

        setRecyclerView();

    }

    public void setRecyclerView(){
        String csiName = getIntent().getStringExtra("CSIName");

        Query query = forumCSIRef.whereEqualTo("csi",csiName).orderBy("csi", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<ForumCSI> options = new FirestoreRecyclerOptions.Builder<ForumCSI>()
                .setQuery(query, ForumCSI.class)
                .build();

        adapter = new ForumCSIAdapter(options);

        RecyclerView rv = findViewById(R.id.rcyViewLoadCSIForum);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}