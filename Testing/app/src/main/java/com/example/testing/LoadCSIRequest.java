package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class LoadCSIRequest extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference csiRef = db.collection("CSI_Request");

    CSIAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_c_s_i_request);

        setRecycler();
    }

    private void setRecycler(){
        Query query = csiRef;

        FirestoreRecyclerOptions<CSI> options = new FirestoreRecyclerOptions.Builder<CSI>()
                .setQuery(query,CSI.class)
                .build();

        adapter = new CSIAdapter(options);

        RecyclerView rv = findViewById(R.id.rcyViewLoadCSIRequest);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == 4){
                    adapter.deleteItem(viewHolder.getAdapterPosition());
                }else {

                    adapter.moveItem(viewHolder.getAdapterPosition());
                }


            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(LoadCSIRequest.this,R.color.colorReject))
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(LoadCSIRequest.this,R.color.colorAccept))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_check)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(rv);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String type = getIntent().getStringExtra("def");
        Intent i = new Intent(LoadCSIRequest.this, Navigation.class);
        i.putExtra("def",type);
        startActivity(i);
    }
}