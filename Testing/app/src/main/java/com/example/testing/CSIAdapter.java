package com.example.testing;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class CSIAdapter extends FirestoreRecyclerAdapter<CSI, CSIAdapter.CSIHolder> {

    private OnItemClickListener listener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference csiRef = db.collection("CSI");

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
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }
    public void moveItem(int position){
        final DocumentReference d = getSnapshots().getSnapshot(position).getReference();

        d.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = documentSnapshot.getString("name");
                String type = documentSnapshot.getString("type");
                String email =documentSnapshot.getString("email");
                String desc = documentSnapshot.getString("description");

                Map<String,Object> data = new HashMap<>();
                data.put("name",name);
                data.put("type",type);
                data.put("email",email);
                data.put("description",desc);

                csiRef.document(""+documentSnapshot.getId()).set(data);
                d.delete();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.println(Log.DEBUG,"dsd",""+e);
            }
        });

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
