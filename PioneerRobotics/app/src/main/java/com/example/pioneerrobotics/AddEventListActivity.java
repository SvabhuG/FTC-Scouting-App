package com.example.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class AddEventListActivity extends AppCompatActivity {


    Button add_team;
    EditText teamNameText, teamNumberText, eventText;
    DatabaseReference events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_list);

        add_team = (Button) findViewById(R.id.add_team);
        teamNameText = (EditText) findViewById(R.id.teamNameText);
        teamNumberText = (EditText)findViewById(R.id.teamNumberText);
        eventText = (EditText) findViewById(R.id.eventText);

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        events = FirebaseDatabase.getInstance().getReference();

        add_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writePost();
            }
        });

    }

    private void writePost(){

        eventData vals = new eventData(teamNameText.getText().toString(),teamNumberText.getText().toString(), eventText.getText().toString());
        Map<String, Object> eventValues = vals.toMap();

        Map<String, Object> eventChildUpdates = new HashMap<>();
        events.child("events").child(eventText.getText().toString()).child(teamNameText.getText().toString()).setValue(eventValues);
        events.updateChildren(eventChildUpdates);
    }

}

