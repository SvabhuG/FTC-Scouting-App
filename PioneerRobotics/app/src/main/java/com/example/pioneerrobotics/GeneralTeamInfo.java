package com.example.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static io.grpc.okhttp.internal.Platform.logger;


public class GeneralTeamInfo extends AppCompatActivity {

    private static final String TAG = "GeneralTeamInfo";

    Toolbar toolbar;
    Button submit_info, add_event_info;
    public static EditText teamNameEditText, teamNumberEditText, eventEditText, scorer, roundEditText;
    private Spinner intakeSpinner;
    private DatabaseReference mDatabase;
    ArrayList eventsOccurred = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_team_info);



        toolbar = (Toolbar)findViewById((R.id.toolbar));
        submit_info = (Button)findViewById(R.id.submit_info);
        teamNameEditText = (EditText)findViewById(R.id.team_name);
        teamNumberEditText = (EditText)findViewById(R.id.team_number);
        eventEditText = (EditText)findViewById(R.id.event);
        scorer = (EditText)findViewById(R.id.scorer);
        roundEditText = (EditText)findViewById(R.id.roundEditText);
        intakeSpinner = (Spinner)findViewById(R.id.intakeSpinner);
        add_event_info = (Button)findViewById(R.id.add_event_page_button);
        mDatabase = FirebaseDatabase.getInstance().getReference("events");

        readFromDatabase();


        List<String> mechs = new ArrayList<String>();
        mechs.add("Forklift");
        mechs.add("Intake Wheels");
        mechs.add("Grabbing Arm");
        mechs.add("Other");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,mechs);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        intakeSpinner.setAdapter(dataAdapter);

        add_event_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity();
            }
        });


        submit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team_name = teamNameEditText.getText().toString();
                String team_number = teamNumberEditText.getText().toString();
                String eventText = eventEditText.getText().toString();
                String scorerText = scorer.getText().toString();
                String roundText = roundEditText.getText().toString();

                if (team_name.isEmpty()){
                    teamNameEditText.setError("Team name is required");
                    teamNameEditText.requestFocus();
                }
                else if (team_number.isEmpty()){
                    teamNumberEditText.setError("Team number is required");
                    teamNumberEditText.requestFocus();
                }
                else if (eventText.isEmpty()){
                    eventEditText.setError("Event name is required");
                    eventEditText.requestFocus();
                }
                else if (scorerText.isEmpty()){
                    scorer.setError("Scorer name is required");
                    scorer.requestFocus();
                }
                else if (roundText.isEmpty()){
                    roundEditText.setError("Scorer name is required");
                    roundEditText.requestFocus();
                }
                else openMainActivity();
            }
        });

    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void AddEventActivity() {
        Intent intent = new Intent(this, AddEventListActivity.class);
        startActivity(intent);
    }

    public void readFromDatabase(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot teamSnapshot : eventSnapshot.getChildren()) {
                        String teamName = teamSnapshot.child("TeamName").getValue(String.class);
                        String teamNum = teamSnapshot.child("TeamNumber").getValue(String.class);
                        eventData eventData = new eventData(teamName,teamNum);
                        Log.i("MyApp","Team Name is " + eventData.getTeamName());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
