package com.cricketgame.pojo;

import com.cricketgame.document.Player;

public class Batsman extends Player {
    public Batsman(double playerRating,String playerName) {
        super(playerRating,playerName);
    }

    @Override
    public String toString() {
        return "Player " + this.playerName + " rating - " + this.playerRating + " runs scored - " + this.noOfRunsScored + " num balls played - " + this.noOfBallsPlayed + " num boundaries - " + this.noOfBoundaries;
    }
}
