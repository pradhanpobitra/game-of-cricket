package cricket;

abstract class Player {
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

    abstract public void showStatsOfPlayer();
    abstract public void updatePlayerCareerStats();
}