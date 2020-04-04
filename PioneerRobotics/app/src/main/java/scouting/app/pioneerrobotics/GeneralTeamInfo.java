package scouting.app.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GeneralTeamInfo extends AppCompatActivity {

    private static final String TAG = "GeneralTeamInfo";

    Toolbar toolbar;
    Button submit_info, add_event_info, analyticsOpen;
    public static TextInputEditText roundEditText;
    public static AutoCompleteTextView teamNameEditText, teamNumberEditText, eventEditText, scorer;
    private DatabaseReference mDatabase;
    private DatabaseReference dataBase;
    public static ArrayList<eventData> eventsOccurred = new ArrayList<>();
    public static ArrayList<AnalyticsData> roundsOccurred = new ArrayList<>();
    List<String> eventNames = new ArrayList<>();
    public static List<String> teamNames = new ArrayList<>();
    public static List<String> teamNumbers = new ArrayList<>();
    public static ArrayAdapter<String> eventAdapter;
    public static int count;
    int databasaCount = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_team_info);
        toolbar = findViewById((R.id.toolbar));
        submit_info = findViewById(R.id.submit_info);
        teamNameEditText = findViewById(R.id.team_name);
        teamNumberEditText = findViewById(R.id.team_number);
        eventEditText = findViewById(R.id.eventEditText);
        scorer = findViewById(R.id.scorer);
        roundEditText = findViewById(R.id.roundEditText);
        add_event_info = findViewById(R.id.add_event_page_button);
        analyticsOpen = findViewById(R.id.analytics);
        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        dataBase = FirebaseDatabase.getInstance().getReference("data");



        if(databasaCount == 0) {
            readFromDatabase();
            databasaCount = 1;
        }

        List<String> scorers = new ArrayList<String>();
        scorers.add("Harsh");
        scorers.add("Allen");
        scorers.add("Krish");
        scorers.add("Svabhu");
        scorers.add("Mahith");
        scorers.add("Keerat");
        scorers.add("Roma");
        scorers.add("Jason");
        scorers.add("Max");
        scorers.add("Tejas");
        scorers.add("Marcus");

        ArrayAdapter<String> scorerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,scorers);

        eventAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,eventNames);

        eventEditText.setAdapter(eventAdapter);

        scorer.setAdapter(scorerAdapter);

        teamNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (count == 0){
                        if (!eventEditText.getText().toString().isEmpty()){
                            for (eventData obj: eventsOccurred) {
                                if (eventEditText.getText().toString().equals(obj.getEvent())){
                                    teamNames.add(obj.getTeamName());
                                    teamNumbers.add(obj.getTeamNumber());
                                }
                                Log.i("teamName", obj.getTeamName());
                            }
                            count = 1;
                        }

                    }
                    ArrayAdapter<String> teamNamesAdapter = new ArrayAdapter<String>(v.getContext(),
                            android.R.layout.simple_list_item_1,teamNames);

                    teamNameEditText.setAdapter(teamNamesAdapter);
                } else{
                    if (!teamNameEditText.getText().toString().isEmpty()){
                        if(teamNames.contains(teamNameEditText.getText().toString())) {
                            teamNumberEditText.setText(teamNumbers.get(teamNames.indexOf(teamNameEditText.getText().toString())));
                        }
                    }
                }
            }
        });

        teamNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    if(teamNameEditText.getText().toString().isEmpty()){
                        if(count==0){
                            if(!eventEditText.getText().toString().isEmpty()){
                                for (eventData obj: eventsOccurred) {
                                    if (eventEditText.getText().toString().equals(obj.getEvent())){
                                        teamNames.add(obj.getTeamName());
                                        teamNumbers.add(obj.getTeamNumber());
                                    }
                                    Log.i("teamName", obj.getTeamName());
                                }

                                count = 1;
                            }


                        }
                        ArrayAdapter<String> teamNumbersAdapter = new ArrayAdapter<String>(v.getContext(),
                                android.R.layout.simple_list_item_1,teamNumbers);

                        teamNumberEditText.setAdapter(teamNumbersAdapter);
                    }
                }else {
                    if(!teamNumberEditText.getText().toString().isEmpty()) {
                        if(teamNumbers.contains(teamNumberEditText.getText().toString()))
                            teamNameEditText.setText(teamNames.get(teamNumbers.indexOf(teamNumberEditText.getText().toString())));
                    }
                }
            }
        });




        add_event_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity();
            }
        });

        analyticsOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnalytics();
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

                boolean numeric = true;

                try {
                    Double num = Double.parseDouble(roundText);
                } catch (NumberFormatException e) {
                    numeric = false;
                }
                if (!numeric){
                    roundEditText.setError("Round must be a number");
                    roundEditText.requestFocus();
                }
                else if (team_name.isEmpty()){
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
    public void openAnalytics(){
        Intent intent = new Intent(this, Analytics.class);
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
                        String teamName = teamSnapshot.child("Team Name").getValue(String.class);
                        String teamNum = teamSnapshot.child("Team Number").getValue(String.class);
                        String event = teamSnapshot.child("Event").getValue(String.class);

                        eventData EventData = new eventData(teamName,teamNum,event);
                        eventsOccurred.add(EventData);
                    }
                    String[] str = eventSnapshot.getRef().toString().split("/");
                    eventNames.add(str[str.length-1].replace("%20"," "));
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot xSnapshot) {
                for (DataSnapshot nobodyCares : xSnapshot.getChildren()){
                    for (DataSnapshot roundSnapshot : nobodyCares.getChildren()){
                        int teamNum = roundSnapshot.child("Info").child("Team Number").getValue(Integer.class);
                        int round = roundSnapshot.child("Info").child("Round").getValue(Integer.class);
                        int skystonesDelivered = roundSnapshot.child("Autonomous").child("Skystones Delivered").getValue(Integer.class);
                        int stonesDelivered = roundSnapshot.child("Autonomous").child("Stones Delivered").getValue(Integer.class);
                        int stonesPlaced = roundSnapshot.child("Autonomous").child("Total Stones Placed").getValue(Integer.class);
                        int autonScore = roundSnapshot.child("Autonomous").child("Autonomous Score").getValue(Integer.class);
                        int foundationTime = roundSnapshot.child("Autonomous").child("Foundation Time").getValue(Integer.class);
                        int autonomousTime = roundSnapshot.child("Autonomous").child("Autonomous Time").getValue(Integer.class);
                        int teleOpScore = roundSnapshot.child("Tele Op").child("TeleOp Score").getValue(Integer.class);
                        int teleHeight = roundSnapshot.child("Tele Op").child("Skyscraper Height").getValue(Integer.class);
                        int teleDelivered = roundSnapshot.child("Tele Op").child("TeleOp Stones Delivered").getValue(Integer.class);
                        int telePlaced = roundSnapshot.child("Tele Op").child("TeleOp Stones Placed").getValue(Integer.class);
                        int capstones = roundSnapshot.child("Endgame").child("Capstones Placed").getValue(Integer.class);
                        int capstoneHeight = roundSnapshot.child("Endgame").child("First Capstone Height").getValue(Integer.class);
                        int secondCapstoneHeight = roundSnapshot.child("Endgame").child("Second Capstone Height").getValue(Integer.class);
                        int totalScore = roundSnapshot.child("Endgame").child("Total Score").getValue(Integer.class);
                        boolean foundationMovedOut = roundSnapshot.child("Endgame").child("Foundation Moved Out").getValue(boolean.class);
                        boolean endParked = roundSnapshot.child("Endgame").child("Parked Endgame").getValue(boolean.class);
                        int endScore = roundSnapshot.child("Endgame").child("Endgame Score").getValue(Integer.class);
                        String teamName = roundSnapshot.child("Info").child("Team Name").getValue(String.class);
                        String eventName = roundSnapshot.child("Info").child("Event Name").getValue(String.class);
                        String role = roundSnapshot.child("Info").child("Team Name").getValue(String.class);
                        String startSide = roundSnapshot.child("Autonomous").child("Starting Side").getValue(String.class);
                        boolean foundationMoved = roundSnapshot.child("Autonomous").child("Foundation Moved").getValue(boolean.class);
                        boolean parked = roundSnapshot.child("Autonomous").child("Parked").getValue(boolean.class);

                        AnalyticsData analytics =  new AnalyticsData(teamNum,round,skystonesDelivered,stonesDelivered,stonesPlaced,autonScore,foundationTime,
                                autonomousTime,teleOpScore,teleHeight,teleDelivered,telePlaced,capstones,capstoneHeight,secondCapstoneHeight,totalScore,
                                foundationMovedOut,endParked,endScore,teamName,eventName,role,startSide,foundationMoved,parked);

                        roundsOccurred.add(analytics);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}


