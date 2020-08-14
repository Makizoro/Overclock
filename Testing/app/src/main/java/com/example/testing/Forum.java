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

public class Forum extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference forumRef = db.collection("Forum");

    private GeneralForumAdapter adapter;

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        setRecyclerView();

        fab = findViewById(R.id.fabAddForumMessage);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Forum.this,AddToForum.class);
                startActivity(i);
            }
        });
    }

    public void setRecyclerView(){
        Query query = forumRef.orderBy("topic", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<GeneralForum> options = new FirestoreRecyclerOptions.Builder<GeneralForum>()
                .setQuery(query, GeneralForum.class)
                .build();

        adapter = new GeneralForumAdapter(options);

        RecyclerView rv = findViewById(R.id.rcyViewLoadForum);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}