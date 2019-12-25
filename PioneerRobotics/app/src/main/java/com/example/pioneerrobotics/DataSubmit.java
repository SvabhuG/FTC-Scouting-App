package com.example.pioneerrobotics;
import java.util.Map;
import android.provider.ContactsContract;
import java.util.HashMap;
public class DataSubmit {
  String teamName;//test fields for now. Can/will change later, are just for test purposes
    int teamNum;
    String eventName;
    String scorerName;

    public DataSubmit(String name, int teamNum, String eventName, String scorerName) {
      this.teamName = name;
      this.teamNum = teamNum;
      this.eventName = eventName;
      this.scorerName = scorerName;
    }

  public Map<String, Object> toMap() {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    fields.put("Team Name", teamName);
    fields.put("Team Number", teamNum);
    fields.put("Event Name", eventName);
    fields.put("Score", scorerName);
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

    public TeleOpData(int teleOpScore, int teleHeight, int teleDelivered, int telePlaced) {
        this.teleOpScore = teleOpScore;
        this.teleHeight = teleHeight;
        this.teleDelivered = teleDelivered;
        this.telePlaced = telePlaced;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("TeleOp Stones Delivered", teleDelivered);
        fields.put("TeleOp Stones Placed", telePlaced);
        fields.put("Skyscraper Height", teleHeight);
        fields.put("Tele-Op Score", teleOpScore);
        return fields;
    }

}

class EndgameData {
    int capstones, capstoneHeight, secondCapstoneHeight;
    boolean foundationMovedOut, endParked;
    int endScore;

    public EndgameData(int capstones, int capstoneHeight, int secondCapstoneHeight, boolean foundationMovedOut, boolean endParked, int endScore) {
        this.capstones = capstones;
        this.capstoneHeight = capstoneHeight;
        this.secondCapstoneHeight = secondCapstoneHeight;
        this.foundationMovedOut = foundationMovedOut;
        this.endParked = endParked;
        this.endScore = endScore;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Capstones Placed", capstones);
        fields.put("First Capstone Height", capstoneHeight);
        fields.put("Second Capstone Height", secondCapstoneHeight);
        fields.put("Foundation Moved Out", foundationMovedOut);
        fields.put("Parked Endgame", endParked);
        fields.put("Endgame Score", endScore);
        return fields;
    }


}

class TeamData {
    String name;
    int teamNum;
    String eventName;
    String scorerName;

}
