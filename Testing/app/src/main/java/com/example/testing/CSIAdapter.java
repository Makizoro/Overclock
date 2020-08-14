package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

public class CSIAdapter extends FirestoreRecyclerAdapter<CSI, CSIAdapter.CSIHolder> {

    private OnItemClickListener listener;

    public CSIAdapter(@NonNull FirestoreRecyclerOptions<CSI> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CSIHolder csiHolder, int i, @NonNull CSI csi) {
        csiHolder.name.setText(csi.getName());
        csiHolder.type.setText(csi.getType());
        csiHolder.email.setText(csi.getEmail());
        csiHolder.desc.setText(csi.getDescription());
    }

    @NonNull
    @Override
    public CSIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_csi,parent,false);
        return new CSIHolder(v);
    }

    class CSIHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView type;
        TextView email;
        TextView desc;

        public CSIHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cVNameCSI);
            type = itemView.findViewById(R.id.cVType);
            email = itemView.findViewById(R.id.cVEmail);
            desc =itemView.findViewById(R.id.cVDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
