package com.example.pioneerrobotics;
import java.util.Map;
import android.provider.ContactsContract;
import java.util.HashMap;
public class DataSubmit {
  String teamName;//test fields for now. Can/will change later, are just for test purposes
    int teamNum, round;
    String eventName;
    String scorerName;

    public DataSubmit(String name, int teamNum, String eventName, String scorerName, int round) {
      this.teamName = name;
      this.teamNum = teamNum;
      this.eventName = eventName;
      this.scorerName = scorerName;
      this.round = round;
    }

  public Map<String, Object> toMap() {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    fields.put("Team Name", teamName);
    fields.put("Team Number", teamNum);
    fields.put("Event Name", eventName);
    fields.put("Scorer", scorerName);
    fields.put("Round", round);
    return fields;
  }

}

class AutonomousData {

    int skystonesDelivered, stonesDelivered, stonesPlaced, autonScore,foundationTime, autonomousTime;
    boolean foundationMoved, parked;
    String alliance, startSide;

    public AutonomousData(int skystonesDelivered, int stonesDelivered, int stonesPlaced, int foundationTime, boolean foundationMoved, boolean parked, String alliance, String startSide, int autonomousTime, int autonScore) {
        this.skystonesDelivered = skystonesDelivered;
        this.stonesDelivered = stonesDelivered;
        this.stonesPlaced = stonesPlaced;
        this.foundationMoved = foundationMoved;
        this.foundationTime = foundationTime;
        this.parked = parked;
        this.alliance = alliance;
        this.startSide = startSide;
        this.autonomousTime = autonomousTime;
        this.autonScore = autonScore;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Skystones Delivered", skystonesDelivered);
        fields.put("Stones Delivered", stonesDelivered);
        fields.put("Total Stones Placed", stonesPlaced);
        fields.put("Foundation Moved", foundationMoved);
        fields.put("Foundation Time", foundationTime);
        fields.put("Parked", parked);
        fields.put("Alliance", alliance);
        fields.put("Starting Side", startSide);
        fields.put("Autonomous Time", autonomousTime);
        fields.put("Autonomous Score",autonScore);
        return fields;
    }


}

class TeleOpData {
    int teleOpScore, teleHeight, teleDelivered, telePlaced;
    String role;

    public TeleOpData(int teleOpScore, int teleHeight, int teleDelivered, int telePlaced, String role) {
        this.teleOpScore = teleOpScore;
        this.teleHeight = teleHeight;
        this.teleDelivered = teleDelivered;
        this.telePlaced = telePlaced;
        this.role = role;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("TeleOp Stones Delivered", teleDelivered);
        fields.put("TeleOp Stones Placed", telePlaced);
        fields.put("Skyscraper Height", teleHeight);
        fields.put("TeleOp Score", teleOpScore);
        fields.put("Role", role);
        return fields;
    }

}

class EndgameData {
    int capstones, capstoneHeight, secondCapstoneHeight, totalScore;
    boolean foundationMovedOut, endParked;
    int endScore;

    public EndgameData(int capstones, int capstoneHeight, int secondCapstoneHeight, boolean foundationMovedOut, boolean endParked, int endScore, int totalScore) {
        this.capstones = capstones;
        this.capstoneHeight = capstoneHeight;
        this.secondCapstoneHeight = secondCapstoneHeight;
        this.foundationMovedOut = foundationMovedOut;
        this.endParked = endParked;
        this.endScore = endScore;
        this.totalScore = totalScore;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Capstones Placed", capstones);
        fields.put("First Capstone Height", capstoneHeight);
        fields.put("Second Capstone Height", secondCapstoneHeight);
        fields.put("Foundation Moved Out", foundationMovedOut);
        fields.put("Parked Endgame", endParked);
        fields.put("Endgame Score", endScore);
        fields.put("Total Score", totalScore);
        return fields;
    }


}

class eventData {

    public eventData(){}

    public eventData(String teamName, String teamNumber, String event) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        this.event = event;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName ) {
        this.teamName = teamName;
    }

    public String getTeamNumber() {
        return this.teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String teamName;
    public String teamNumber;

    public String getEvent() {
        return event;
    }

    public String event;


    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Team Name", teamName);
        fields.put("Team Number", teamNumber);
        fields.put("Event", event);
        return fields;
    }
}
