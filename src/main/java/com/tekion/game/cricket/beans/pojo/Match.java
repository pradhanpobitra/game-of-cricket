package com.tekion.game.cricket.beans.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {
    private static int matchIdCount = 0;
    private final List<String> playingEleven = new ArrayList<>();
    private final Map<String,Integer> runsScoredByPlayers = new HashMap<>();
    private final int matchId;
    private final int numOfOvers;
    private final Team team1, team2;
    private int targetScore;
    private boolean isTargetSet;
    private boolean hasMatchEnded;
    private Player manOfTheMatch;
    private Team winningTeam;

    public Match(final int numOfOvers,final Team team1,final Team team2) {
        this.numOfOvers = numOfOvers;
        this.matchId = ++matchIdCount;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Map<String, Integer> getRunsScoredByPlayers() {
        return runsScoredByPlayers;
    }

    public void setRunsScoredByPlayers() {
        final List<Player> team1playerList = team1.getPlayerList();
        final List<Player> team2playerList = team2.getPlayerList();
        for(final Player player : team1playerList) {
            runsScoredByPlayers.put(player.getPlayerName(), player.getNoOfRunsScored());
        }
        for(final Player player : team2playerList) {
            runsScoredByPlayers.put(player.getPlayerName(), player.getNoOfRunsScored());
        }
    }

    public int getMatchId() {
        return matchId;
    }

    public int getNumOfOvers() {
        return numOfOvers;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(final int targetScore) {
        this.targetScore = targetScore;
    }

    public boolean isTargetSet() {
        return isTargetSet;
    }

    public void setTargetSet(final boolean targetSet) {
        isTargetSet = targetSet;
    }

    public void setHasMatchEnded(final boolean hasMatchEnded) {
        this.hasMatchEnded = hasMatchEnded;
    }

    public Player getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch() {
        final Player highestScorerTeam1 = team1.getHighestScorer();
        final Player highestScorerTeam2 = team2.getHighestScorer();

        manOfTheMatch = ( highestScorerTeam1.getNoOfRunsScored() > highestScorerTeam2.getNoOfRunsScored() ? highestScorerTeam1 : highestScorerTeam2 );
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam() {
        if(team1.getTeamScore() == team2.getTeamScore()) {
            winningTeam = null;
            return;
        }
        winningTeam = team1.getTeamScore() > team2.getTeamScore() ? team1 : team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public List<String> getPlayingEleven() {
        return playingEleven;
    }

    public void setPlayingEleven() {
        playingEleven.addAll(team1.getPlayerNamesList());
        playingEleven.add("");
        playingEleven.addAll(team2.getPlayerNamesList());
    }
}
