package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference eventRef = db.collection("Event");

    EditText edtEventCSIName;
    EditText edtMEventName;
    EditText edtEventDate;
    EditText edtEventVenue;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        edtEventCSIName = findViewById(R.id.edtEventCSIName);
        edtEventDate = findViewById(R.id.edtEventDate);
        edtEventVenue = findViewById(R.id.edtVenue);
        edtMEventName = findViewById(R.id.edtEventName);
        btnSave = findViewById(R.id.btnSaveEvent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String csi = edtEventCSIName.getText().toString();
                String eventName = edtMEventName.getText().toString();
                String eventDate = edtEventDate.getText().toString();
                String eventVenue = edtEventVenue.getText().toString();

                Map<String, Object> data = new HashMap<>();
                data.put("csi",csi);
                data.put("name",eventName);
                data.put("date",eventDate);
                data.put("venue",eventVenue);

                eventRef.add(data);

                Intent i = new Intent(AddEvent.this, Events.class);
                startActivity(i);

            }
        });


    }
}