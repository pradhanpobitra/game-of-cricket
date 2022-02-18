package com.cricketgame.pojo;

public class Batsman extends Player {

    public Batsman(double playerRating,String playerName) {
        super(playerRating,playerName);
    }

    @Override
    public void showStatsOfPlayer() {
        System.out.println("Player " + this.playerName + " rating - " + this.playerRating + " runs scored - " + this.noOfRunsScored + " num balls played - " + this.noOfBallsPlayed + " num boundaries - " + this.noOfBoundaries);
    }
}
