package com.cricketgame.pojo;

import com.cricketgame.document.Player;

public class Bowler extends Player {
    private int noOfOversBalled;
    private int noOfMaidenOversBalled;
    private int noOfWicketsTaken;
    private int runsGiven;

    public int getNoOfOversBalled() {
        return noOfOversBalled;
    }

    public int getNoOfMaidenOversBalled() {
        return noOfMaidenOversBalled;
    }

    public int getNoOfWicketsTaken() {
        return noOfWicketsTaken;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public Bowler(double playerRating, String playerName) {
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

    public void setCareerNumOversBowled(int overs) {
        noOfOversBalled += overs;
    }

    public void setCareerMaidenOversBalled(int overs) {
        noOfMaidenOversBalled += overs;
    }

    public void setCareerWicketsTaken(int wicketsTaken) {
        noOfWicketsTaken += wicketsTaken;
    }

    public void setCareerRunsGiven(int runs) {
        runsGiven += runs;
    }
    @Override
    public String toString() {
        return "Player " + this.playerName + " rating - " + this.playerRating + " runs scored - " + this.noOfRunsScored + " num balls played - " + this.noOfBallsPlayed + " num boundaries - " + this.noOfBoundaries
         + "num overs bowled - " + this.noOfOversBalled + " num wickets taken - " + this.noOfWicketsTaken + " runs given - " + this.runsGiven + " num maiden overs bowled - " + this.noOfMaidenOversBalled;
    }
}
