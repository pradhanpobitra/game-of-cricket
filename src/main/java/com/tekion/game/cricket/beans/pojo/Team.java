package com.tekion.game.cricket.beans.pojo;

import com.tekion.game.cricket.utils.MatchUtil;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String teamName;
    private int teamScore;
    private int teamWicketsLost;
    private int currentBatsmanIndex;
    private int currentBowlerIndex;
    private List<Player> playerList;
    private final List<Bowler> bowlerList = new ArrayList<>();

    public Team(final List<Player> playerList,final List<Player> bowlers,final String teamName) {
        this.playerList = playerList;
        this.teamName = teamName;
        for(Player p : bowlers) {
            this.bowlerList.add((Bowler) p);
        }
    }

    public void restoreDataFields() {
        teamScore = teamWicketsLost = currentBatsmanIndex = currentBowlerIndex = 0;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(final int teamScore) {
        this.teamScore = teamScore;
    }

    public int getTeamWicketsLost() {
        return teamWicketsLost;
    }

    public void setTeamWicketsLost(final int teamWicketsLost) {
        this.teamWicketsLost = teamWicketsLost;
    }

    public int getCurrentBatsmanIndex() {
        return currentBatsmanIndex;
    }

    public void setCurrentBatsmanIndex(final int currentBatsmanIndex) {
        this.currentBatsmanIndex = currentBatsmanIndex;
    }

    public int getCurrentBowlerIndex() {
        return currentBowlerIndex;
    }

    public void setCurrentBowlerIndex(final int currentBowlerIndex) {
        this.currentBowlerIndex = currentBowlerIndex;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public String getTeamName() {
        return teamName;
    }

    public Bowler getCurrentBowler() {
        return bowlerList.get(currentBowlerIndex);
    }

    public Player getCurrentBatsman() {
        return playerList.get(currentBatsmanIndex);
    }

    public Player getHighestScorer() {
        Player highestScorer = playerList.get(0);
        for(int num = 1; num < MatchUtil.NUM_PLAYERS; num++) {
            if(playerList.get(num).getNoOfRunsScored() > highestScorer.getNoOfRunsScored()) {
                highestScorer = playerList.get(num);
            }
        }
        return highestScorer;
    }

    public List<String> getPlayerNamesList() {
        final List<String> list = new ArrayList<>();
        for(Player p : playerList) {
            list.add(p.getPlayerName());
        }
        return list;
    }
}
