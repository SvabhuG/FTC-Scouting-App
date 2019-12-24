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

    int skystonesDelivered, stonesDelivered, stonesPlaced, autonScore;
    boolean foundationMoved, parked;
    String alliance, startSide;

    public AutonomousData(int skystonesDelivered, int stonesDelivered, int stonesPlaced, boolean foundationMoved, boolean parked, String alliance, String startSide, int autonScore) {
        this.skystonesDelivered = skystonesDelivered;
        this.stonesDelivered = stonesDelivered;
        this.stonesPlaced = stonesPlaced;
        this.foundationMoved = foundationMoved;
        this.parked = parked;
        this.alliance = alliance;
        this.startSide = startSide;
        this.autonScore = autonScore;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Skystones Delivered", skystonesDelivered);
        fields.put("Stones Delivered", stonesDelivered);
        fields.put("Total Stones Placed", stonesPlaced);
        fields.put("Foundation Moved", foundationMoved);
        fields.put("Parked", parked);
        fields.put("Alliance", alliance);
        fields.put("Starting Side", startSide);
        fields.put("Autonomous Score",autonScore);
        return fields;
    }


}

class TeleOpData {
    int teleOpScore, tele_height, tele_delivered, tele_placed;

    public TeleOpData(int teleOpScore, int teleHeight, int teleDelivered, int telePlaced) {
        this.teleOpScore = teleOpScore;
        this.teleHeight = tele_height;
        this.teleDelivered = tele_delivered;
        this.telePlaced = tele_placed;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("Stones Delivered", tele_delivered);
        fields.put("Stones Placed", tele_placed);
        fields.put("Skyscraper Height", tele_height);
        fields.put("Tele-Op Score", tele_delivered);
        return fields;
    }

}

class EndgameData {
    int capstones;
    int capstoneHeight;
    int secondCaptsoneHeight;
    boolean foundationMoved, parked;
    int endgameScore;
}

class TeamData {
    String name;
    int teamNum;
    String eventName;
    String scorerName;

}
