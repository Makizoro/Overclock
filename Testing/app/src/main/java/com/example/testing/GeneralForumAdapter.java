package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class GeneralForumAdapter extends FirestoreRecyclerAdapter<GeneralForum, GeneralForumAdapter.GeneralForumHolder> {

    public GeneralForumAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GeneralForumHolder generalForumHolder, int i, @NonNull GeneralForum generalForum) {
         generalForumHolder.txtTopic.setText(generalForum.getTopic());
         generalForumHolder.txtMessage.setText(generalForum.getMessage());
         generalForumHolder.txtUsername.setText(generalForum.getUsername());
    }

    @NonNull
    @Override
    public GeneralForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_forum_messages,
                parent,false);

        return new GeneralForumHolder(v);
    }

    class GeneralForumHolder extends RecyclerView.ViewHolder{

        TextView txtTopic;
        TextView txtMessage;
        TextView txtUsername;

        public GeneralForumHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txtForumTopic);
            txtMessage = itemView.findViewById(R.id.txtForumMessage);
            txtUsername = itemView.findViewById(R.id.txtForumUsername);

        }
    }
}
