package com.cricketgame.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import java.util.ArrayList;

@Document(indexName = "match")
@Setting(settingPath = "static/es-settings.json")
public class Match {
    private static int matchIdCount = 0;
    private final Team team1, team2;

    @Id
    @Field(type = FieldType.Keyword)
    private final int matchId;

    private final int numOfOvers;
    private int targetScore;
    private boolean isTargetSet;
    private boolean hasMatchEnded;
    private Player manOfTheMatch;
    private Team winningTeam;

    public Match(int numOfOvers,Team team1,Team team2) {
        this.numOfOvers = numOfOvers;
        this.matchId = ++matchIdCount;
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getNumOvers() {
        return numOfOvers;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public String getRunsScoredByPlayer(String playerName) {
        String runsScored = team1.getRunsScoredByPlayer(playerName);
        if(runsScored == null) {
            runsScored = team2.getRunsScoredByPlayer(playerName);
        }
        if(runsScored == null) {
            return "No such player in this match.";
        }
        return runsScored;
    }

    public String getManOfTheMatch() {
        return manOfTheMatch.getPlayerName();
    }

    public ArrayList<String> getAllPlayers() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(team1.getAllPlayers());
        list.addAll(team2.getAllPlayers());
        return list;
    }

    public void setTargetScore(int i) {
        targetScore = i;
        isTargetSet = true;
    }

    public void setHasMatchEnded(boolean b) {
        hasMatchEnded = b;
    }

    public boolean getIsTargetSet() {
        return isTargetSet;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public void setManOfTheMatch(Player manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public void setWinningTeam(Team team) {
        winningTeam = team;
    }

    @Override
    public String toString() {
        return "Match{" +
                "team1=" + team1.getTeamName() +
                ", team2=" + team2.getTeamName() +
                ", matchId=" + matchId +
                ", numOfOvers=" + numOfOvers +
                ", targetScore=" + targetScore +
                ", hasMatchEnded=" + hasMatchEnded +
                ", manOfTheMatch=" + manOfTheMatch.getPlayerName() +
                ", winningTeam=" + winningTeam.getTeamName() +
                '}';
    }
}
