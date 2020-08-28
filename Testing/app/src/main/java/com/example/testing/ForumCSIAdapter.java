package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.zip.Inflater;

public class ForumCSIAdapter extends FirestoreRecyclerAdapter<ForumCSI, ForumCSIAdapter.ForumCSIHolder>{

    public ForumCSIAdapter(@NonNull FirestoreRecyclerOptions<ForumCSI> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ForumCSIHolder forumCSIHolder, int i, @NonNull ForumCSI forumCSI) {
        forumCSIHolder.txtTopic.setText(forumCSI.getTopic());
        forumCSIHolder.txtMessage.setText(forumCSI.getMessage());
        forumCSIHolder.txtUsername.setText(forumCSI.getUsername());
    }

    @NonNull
    @Override
    public ForumCSIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_forum_messages,
                parent,false);
        return new ForumCSIHolder(v);
    }

    class ForumCSIHolder extends RecyclerView.ViewHolder {

        TextView txtTopic;
        TextView txtMessage;
        TextView txtUsername;

        public ForumCSIHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtForumUsername);
            txtMessage = itemView.findViewById(R.id.txtForumMessage);
            txtTopic = itemView.findViewById(R.id.txtForumTopic);
        }
    }

}
