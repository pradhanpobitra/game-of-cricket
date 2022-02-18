package com.cricketgame.repository;

import java.util.HashMap;

public class TeamRepo {
    private static final HashMap<String,Integer> numTeamWins = new HashMap<>();

    public static String getNumOfTeamWins(String teamName) {
        if( ! numTeamWins.containsKey(teamName) ) {
            return "No team found with the given team name.";
        }
        return numTeamWins.get(teamName).toString();
    }

    public static void increamentTeamWins(String teamName) {
        if( ! numTeamWins.containsKey(teamName) ) {
            numTeamWins.put(teamName,1);
            return;
        }
        Integer wins = numTeamWins.get(teamName);
        numTeamWins.put(teamName,wins + 1);
    }
}
