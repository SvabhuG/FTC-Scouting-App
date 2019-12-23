package com.example.pioneerrobotics;

public class DataSubmit {

}

class AutonomusData {
    int skystonesMoved;
    int stonesMoved;
    int autonScore;
    boolean foundationMoved, parked;
    boolean red, loadingStart;

}

class TeleOpData {
    int teleOpScore;
    int height;
    int delivered, placed;
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
