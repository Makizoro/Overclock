package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NotificationAdapter extends FirestoreRecyclerAdapter<Notification, NotificationAdapter.NotificationHolder> {

    public NotificationAdapter(@NonNull FirestoreRecyclerOptions<Notification> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationHolder notificationHolder, int i, @NonNull Notification notification) {
        notificationHolder.txtCsi.setText(notification.getCsi());
        notificationHolder.txtNotify.setText(notification.getNotification());
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_notificcation, parent
        , false);

        return new NotificationHolder(v);
    }

    class NotificationHolder extends RecyclerView.ViewHolder{
        TextView txtCsi;
        TextView txtNotify;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            txtCsi = itemView.findViewById(R.id.txtNotifyCSI);
            txtNotify = itemView.findViewById(R.id.txtNotification);
        }
    }
}
