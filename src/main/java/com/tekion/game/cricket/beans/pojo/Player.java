package com.tekion.game.cricket.beans.pojo;

abstract public class Player {
    private final String playerName;
    private final double playerRating;
    private int noOfBallsPlayed;
    private int noOfBoundaries;
    private int noOfRunsScored;

    protected Player(final double playerRating,final String playerName) {
        this.playerRating = playerRating;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getPlayerRating() {
        return playerRating;
    }

    public int getNoOfBallsPlayed() {
        return noOfBallsPlayed;
    }

    public void setNoOfBallsPlayed(final int noOfBallsPlayed) {
        this.noOfBallsPlayed = noOfBallsPlayed;
    }

    public int getNoOfBoundaries() {
        return noOfBoundaries;
    }

    public void setNoOfBoundaries(final int noOfBoundaries) {
        this.noOfBoundaries = noOfBoundaries;
    }

    public int getNoOfRunsScored() {
        return noOfRunsScored;
    }

    public void setNoOfRunsScored(final int noOfRunsScored) {
        this.noOfRunsScored = noOfRunsScored;
    }
}
