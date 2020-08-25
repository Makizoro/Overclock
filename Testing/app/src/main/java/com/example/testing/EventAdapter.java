package com.example.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EventAdapter extends FirestoreRecyclerAdapter<Event,EventAdapter.EventHolder> {

    public EventAdapter(@NonNull FirestoreRecyclerOptions<Event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EventHolder eventHolder, int i, @NonNull Event event) {
        eventHolder.txtEventVenue.setText(event.getVenue());
        eventHolder.txtEventName.setText(event.getName());
        eventHolder.txtEventDate.setText(event.getDate());
        eventHolder.txtEventCSI.setText(event.getCsi());
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_events,
                parent,false);

        return new EventHolder(v);
    }

    class EventHolder extends RecyclerView.ViewHolder{

        TextView txtEventName;
        TextView txtEventDate;
        TextView txtEventVenue;
        TextView txtEventCSI;

        public EventHolder(@NonNull View itemView) {
            super(itemView);

            txtEventCSI = itemView.findViewById(R.id.txtEventCSI);
            txtEventDate = itemView.findViewById(R.id.txtEventDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            txtEventVenue = itemView.findViewById(R.id.txtEventVenue);
        }
    }
}
