package cricket;

class Player {
    private final int playerNumber;
    private int runsScored;
    private boolean currentlyPlaying;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.runsScored = 0;
        this.currentlyPlaying = false;
    }

    public void setRunsScored(int run) {
        this.runsScored += run;
        this.currentlyPlaying = true;
    }

    public void setPlayerOut() {
        this.currentlyPlaying = false;
    }

    public String getPlayerDetails() {
        String details = "Player " + this.playerNumber + ": " + this.runsScored;
        return this.currentlyPlaying ? details + '*' : details;
    }
}
