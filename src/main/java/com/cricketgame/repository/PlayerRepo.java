package com.cricketgame.repository;

import com.cricketgame.pojo.Player;

import java.util.HashMap;

public class PlayerRepo {
    private static final HashMap<String, Player> playerTournamentStats = new HashMap<>();

    public static String getRunsScoredByPlayerInTournament(String playerName) {
        if( ! playerTournamentStats.containsKey(playerName.toUpperCase()) ) {
            return "No such player played in the tournament.";
        }
        Player player = playerTournamentStats.get(playerName.toUpperCase());
        return player.getNoOfRunsScored().toString();
    }

    public static void updatePlayerTournamentStats(String playerName, Player player) {
        if ( ! playerTournamentStats.containsKey(playerName) ) {
            // at first clone the player object and then put it in the hashmap.

            playerTournamentStats.put(playerName,player);
            return;
        }
        Player p = playerTournamentStats.get(playerName);
        p.increamentCareerNumBallsPlayed(player.getNoOfBallsPlayed());
        p.increamentCareerNumBoundaries(player.getNoOfBoundaries());
        p.increamentCareerNumRunsScored(player.getNoOfRunsScored());
    }
}
