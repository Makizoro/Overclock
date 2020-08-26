package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CSISubsAdapter extends FirestoreRecyclerAdapter<CSISubs,CSISubsAdapter.CSISubsHolder> {

    public CSISubsAdapter(@NonNull FirestoreRecyclerOptions<CSISubs> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CSISubsHolder csiSubsHolder, int i, @NonNull CSISubs csiSubs) {
        csiSubsHolder.csiName.setText(csiSubs.getCsi());
    }

    @NonNull
    @Override
    public CSISubsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_subs,
                parent,false);


        return new CSISubsHolder(v);
    }

    class CSISubsHolder extends RecyclerView.ViewHolder{
        TextView csiName;

        public CSISubsHolder(@NonNull View itemView) {
            super(itemView);
            csiName = itemView.findViewById(R.id.cVSubsNameCSI);
        }
    }
}
