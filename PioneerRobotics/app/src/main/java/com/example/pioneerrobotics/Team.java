package com.example.pioneerrobotics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Team {
    String teamName;
    String teamNumber;
    String event;
    String scorer;

    public Team(){

    }
    public Team(String teamName,String teamNumber,String event,String scorer) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        this.event = event;
        this.scorer = scorer;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public String getEvent() {
        return event;
    }

    public String getScorer() {
        return scorer;
    }
}
