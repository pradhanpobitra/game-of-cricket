package com.tekion.game.cricket.beans.pojo;

public class Bowler extends Player {
    private int noOfOversBalled;
    private int noOfMaidenOversBalled;
    private int noOfWicketsTaken;
    private int runsGiven;

    public Bowler(final double playerRating,final String playerName) {
        super(playerRating, playerName);
    }

    public int getNoOfOversBalled() {
        return noOfOversBalled;
    }

    public void setNoOfOversBalled(final int noOfOversBalled) {
        this.noOfOversBalled = noOfOversBalled;
    }

    public int getNoOfMaidenOversBalled() {
        return noOfMaidenOversBalled;
    }

    public void setNoOfMaidenOversBalled(final int noOfMaidenOversBalled) {
        this.noOfMaidenOversBalled = noOfMaidenOversBalled;
    }

    public int getNoOfWicketsTaken() {
        return noOfWicketsTaken;
    }

    public void setNoOfWicketsTaken(final int noOfWicketsTaken) {
        this.noOfWicketsTaken = noOfWicketsTaken;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(final int runsGiven) {
        this.runsGiven = runsGiven;
    }
}
