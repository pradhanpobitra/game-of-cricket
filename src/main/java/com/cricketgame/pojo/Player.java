package com.cricketgame.pojo;

import com.cricketgame.repository.PlayerRepo;

public abstract class Player {
    protected final double playerRating;
    protected final String playerName;
    protected int noOfBallsPlayed;
    protected int noOfBoundaries;
    protected int noOfRunsScored;

    protected Player(double playerRating,String playerName) {
        this.playerRating = playerRating;
        this.playerName = playerName;
    }

    public void increamentNumRunsScored(int runs) {
        noOfRunsScored += runs;
    }

    public void increamentNumBallsPlayed() {
        noOfBallsPlayed++;
    }

    public void increamentNumBoundaries() {
        noOfBoundaries++;
    }

    public double getPlayerRating() {
        return playerRating;
    }

    public int getNoOfBallsPlayed() {
        return noOfBallsPlayed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getNoOfRunsScored() {
        return noOfRunsScored;
    }

    public int getNoOfBoundaries() {
        return noOfBoundaries;
    }

    public void updatePlayerCareerStats() {
        PlayerRepo.updatePlayerTournamentStats(playerName,this);
    }

    public void increamentCareerNumBallsPlayed(int noOfBallsPlayed) {
        this.noOfBallsPlayed += noOfBallsPlayed;
    }

    public void increamentCareerNumBoundaries(int noOfBoundaries) {
        this.noOfBoundaries += noOfBoundaries;
    }

    public void increamentCareerNumRunsScored(Integer noOfRunsScored) {
        this.noOfRunsScored += noOfRunsScored;
    }

    abstract public void showStatsOfPlayer();
}