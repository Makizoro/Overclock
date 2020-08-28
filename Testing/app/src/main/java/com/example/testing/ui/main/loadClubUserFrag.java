package com.example.testing.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.CSI;
import com.example.testing.CSIAdapter;
import com.example.testing.Profile;
import com.example.testing.ProfilePageCSI;
import com.example.testing.ProfileUpdate;
import com.example.testing.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class loadClubUserFrag extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference csiRef = db.collection("CSI");

    private CSIAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.load_club_user_frag_layout,null);
        RecyclerView rv = root.findViewById(R.id.rcyViewLoadClubUser);
        setRecyclerView(rv);
        return root;
    }

    public void setRecyclerView(RecyclerView rv){
        Query query = csiRef.whereEqualTo("type","Club")
                .orderBy("name",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<CSI> options = new FirestoreRecyclerOptions.Builder<CSI>()
                .setQuery(query,CSI.class)
                .build();
        adapter = new CSIAdapter(options);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new CSIAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String documentId = documentSnapshot.getId();

                String type = getActivity().getIntent().getStringExtra("def");
                Intent i = new Intent(getActivity(), ProfilePageCSI.class);
                i.putExtra("CSIDocumentID",documentId);
                i.putExtra("def",type);
                startActivity(i);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.stopListening();
    }
}
