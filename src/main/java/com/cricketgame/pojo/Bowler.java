package com.cricketgame.pojo;

import java.util.HashMap;

public class Bowler extends Player {
    private static final HashMap<String,Bowler> bowlerTournamentStats = new HashMap<String,Bowler>();
    private int noOfOversBalled;
    private int noOfMaidenOversBalled;
    private int noOfWicketsTaken;
    private int runsGiven;

    public Bowler(double playerRating,String playerName) {
        super(playerRating,playerName);
    }

    public void increamentNumWicketsTaken() {
        noOfWicketsTaken++;
    }

    public void increamentNumOversBalled() {
        noOfOversBalled++;
    }

    public void increamentRunsGiven(int runs) {
        runsGiven += runs;
    }

    public void increamentMaidenOversBowled() {
        noOfMaidenOversBalled++;
    }

    @Override
    public void updatePlayerCareerStats() {
        String bowlerName = playerName.toUpperCase();
        if ( ! bowlerTournamentStats.containsKey(bowlerName) ) {
            bowlerTournamentStats.put(bowlerName,this);
            return;
        }
        Bowler bowler = bowlerTournamentStats.get(bowlerName);
        bowler.noOfOversBalled += noOfOversBalled;
        bowler.noOfMaidenOversBalled += noOfMaidenOversBalled;
        bowler.noOfWicketsTaken += noOfWicketsTaken;
        bowler.runsGiven += runsGiven;
        bowler.noOfBallsPlayed += noOfBallsPlayed;
        bowler.noOfBoundaries += noOfBoundaries;
        bowler.noOfRunsScored += noOfRunsScored;
    }

    @Override
    public void showStatsOfPlayer() {
        System.out.println("Player " + this.playerName + " rating - " + this.playerRating + " runs scored - " + this.noOfRunsScored + " num balls played - " + this.noOfBallsPlayed + " num boundaries - " + this.noOfBoundaries);
        System.out.println("num overs bowled - " + this.noOfOversBalled + " num wickets taken - " + this.noOfWicketsTaken + " runs given - " + this.runsGiven + " num maiden overs bowled - " + this.noOfMaidenOversBalled);
    }
}
