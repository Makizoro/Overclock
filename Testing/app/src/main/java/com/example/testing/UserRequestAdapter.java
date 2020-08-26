package com.example.testing;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class UserRequestAdapter extends FirestoreRecyclerAdapter<User_Request, UserRequestAdapter.UserRequestHolder> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference csiUserRef = db.collection("Person");
    private CollectionReference csiRef = db.collection("CSI");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public UserRequestAdapter(@NonNull FirestoreRecyclerOptions<User_Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserRequestHolder userRequestHolder, int i, @NonNull User_Request user_request) {
        userRequestHolder.txtUsername.setText(user_request.getUsername());
        userRequestHolder.txtUserType.setText(user_request.getType());
        userRequestHolder.txtCSIName.setText(user_request.getCsi());
    }

    @NonNull
    @Override
    public UserRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_user_requests,
                parent,false);

        return new UserRequestHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }
    public void moveItem(int position){
        final DocumentReference d = getSnapshots().getSnapshot(position).getReference();

        d.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString("username");
                String type = documentSnapshot.getString("type");
                String csi =documentSnapshot.getString("csi");
                String id = documentSnapshot.getString("id");

                Map<String,Object> data = new HashMap<>();
                data.put("username",username);
                data.put("type",type);
                data.put("csi",csi);

                csiUserRef.document(""+id).collection("Subscriptions")
            .add(data);
                csiRef.document(""+user.getUid()).collection("Subscriptions")
                        .add(data);
                d.delete();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.println(Log.DEBUG,"dsd",""+e);
            }
        });

    }

    class UserRequestHolder extends RecyclerView.ViewHolder {

        TextView txtCSIName;
        TextView txtUsername;
        TextView txtUserType;

        public UserRequestHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txt_UserName);
            txtUserType = itemView.findViewById(R.id.txt_Type);
            txtCSIName = itemView.findViewById(R.id.txt_CSI);
        }




    }
}
