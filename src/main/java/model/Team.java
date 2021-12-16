package model;

import view.RoadMapsView;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    private static HashMap<String,Team>teams = new HashMap<>();
    private String name;
    private String leader;
    private final ArrayList<String>membersAsideFromLeader = new ArrayList<>();
    private final ArrayList<String>teamTasks = new ArrayList<>();
    private Board board;
    private Chatroom chatroom;
    private RoadMap roadMap;


    public static Team getTeamByName(String name){
        return teams.get(name);
    }






}
