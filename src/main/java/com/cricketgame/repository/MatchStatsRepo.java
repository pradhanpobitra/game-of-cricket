package com.cricketgame.repository;

import com.cricketgame.pojo.Match;
import java.util.ArrayList;
import java.util.HashMap;

public class MatchStatsRepo {
    private static final HashMap<Integer,Match> matchIdToMatchStats = new HashMap<>();
    private static final HashMap<String, ArrayList<Match>> matchesPlayerWasManOfTheMatch = new HashMap<>();

    public static String getRunsScoredByPlayerInMatch(String playerName, int matchId) {
        if( ! matchIdToMatchStats.containsKey(matchId) ) {
            return "Match with given match ID doesnt exist";
        }
        Match matchStats = matchIdToMatchStats.get(matchId);
        return matchStats.getRunsScoredByPlayer(playerName);
    }

    public static ArrayList<String> getPlayingElevenOfMatch(int matchId) {
        ArrayList<String> list = new ArrayList<>();
        if( ! matchIdToMatchStats.containsKey(matchId) ) {
            list.add("Match with given match ID doesnt exist");
            return list;
        }
        Match matchStats = matchIdToMatchStats.get(matchId);
        return matchStats.getAllPlayers();
    }

    public static String getManOfTheMatchWithMatchId(int matchId) {
        if( ! matchIdToMatchStats.containsKey(matchId) ) {
            return "Match with given match ID doesnt exist";
        }
        Match matchStats = matchIdToMatchStats.get(matchId);
        return matchStats.getManOfTheMatch();
    }

    public static String getMatchStatsWithMatchId(int matchId) {
        if( ! matchIdToMatchStats.containsKey(matchId) ) {
            return "No match with the given match ID found.";
        }
        Match matchStats = matchIdToMatchStats.get(matchId);
        return matchStats.toString();
    }

    // MOTM --> Man Of The Match
    public static ArrayList<String> getMatchesPlayerGotMOTM(String playerName) {
        ArrayList<String> list = new ArrayList<>();
        if( ! matchesPlayerWasManOfTheMatch.containsKey(playerName) ) {
            list.add("Player " + playerName + " hasnt won any Man Of the Match awards.");
            return list;
        }
        ArrayList<Match> listOfMatches = matchesPlayerWasManOfTheMatch.get(playerName);
        for(Match match : listOfMatches) {
            list.add("matchId : " + match.getMatchId() + " Winning Team: " + match.getWinningTeamName());
        }
        return list;
    }

    public static void storeMOTM(String nameOfManOfTheMatch, Match match) {
        if( ! matchesPlayerWasManOfTheMatch.containsKey(nameOfManOfTheMatch) ) {
            ArrayList<Match> listOfMatches = new ArrayList<>();
            listOfMatches.add(match);
            matchesPlayerWasManOfTheMatch.put(nameOfManOfTheMatch,listOfMatches);
            return;
        }
        matchesPlayerWasManOfTheMatch.get(nameOfManOfTheMatch).add(match);
    }

    public static void storeMatchStats(int matchId, Match match) {
        matchIdToMatchStats.put(matchId,match);
    }
}
