package com.cricketgame.pojo;

import com.cricketgame.repository.Players;
import com.cricketgame.repository.TeamList;
import com.cricketgame.repository.TeamRepo;
import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    private int teamScore;
    private int teamWicketsLost;
    private int currentBatsmanIndex;
    private int currentBowlerIndex;
    private ArrayList<Bowler> bowlers = new ArrayList<Bowler>();
    private final ArrayList<Player> player;
    private final String teamName;

    public Team(Team team) {
        this.bowlers = team.bowlers;
        this.player = team.player;
        this.teamName = team.teamName;
    }

    public Team(ArrayList<Player> player,ArrayList<Player> bowlers,String teamName) {
        this.player = player;
        this.teamName = teamName;
        for(Player p : bowlers) {
            this.bowlers.add((Bowler) p);
        }
    }

    public ArrayList<String> getAllPlayers() {
        ArrayList<String> listPlayers = new ArrayList<>();
        for(Player p : player) {
            listPlayers.add(p.getPlayerName());
        }
        return listPlayers;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getNumOfWicketsLost() {
        return teamWicketsLost;
    }

    public String getTeamName() {
        return teamName;
    }

    public Player getCurrentBatsman() {
        return player.get(currentBatsmanIndex);
    }

    public Bowler getCurrentBowler() {
        int currBowlerIndex = currentBowlerIndex++;
        currentBowlerIndex %= 5;
        return bowlers.get(currBowlerIndex);
    }

    public Player getHighestScorer() {
        Player highestScorer = player.get(0);
        for(int num = 1; num < MatchUtil.NUM_PLAYERS; num++) {
            if(highestScorer.noOfRunsScored < player.get(num).noOfRunsScored) {
                highestScorer = player.get(num);
            }
        }
        return highestScorer;
    }

    public void setNextBatsmanOnStrike() {
        currentBatsmanIndex++;
    }

    public void increamentTeamScore(int runs) {
        teamScore += runs;
    }

    public void increamentWicketsLost() {
        teamWicketsLost++;
    }

    public void updateTeamPlayersStats() {
        for(int num = 0; num < MatchUtil.NUM_PLAYERS; num++) {
            player.get(num).updatePlayerCareerStats();
        }
    }

    public void showTeamScoreBoard() {
        System.out.println("Team - " + teamName + " stats :");
        System.out.println(teamScore + "/" + teamWicketsLost);
        for(int  num = 0; num < MatchUtil.NUM_PLAYERS ; num++) {
            player.get(num).showStatsOfPlayer();
        }
    }

    public void storePlayerStatsInTeam(HashMap<String, Player> playerStats) {
        for(int num = 0; num < MatchUtil.NUM_PLAYERS; num++) {
            playerStats.put(player.get(num).getPlayerName(),player.get(num));
        }
    }

    public void increamentTeamWins() {
        TeamRepo.increamentTeamWins(teamName);
    }
}
