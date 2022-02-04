package cricket;

class Team {
    private Player[] player;
    private int teamScore;
    private int teamWicketsDown;
    private int currentPlayer;

    public Team() {
        this.player = new Player[11];
        for(int num=0; num < 11; num++) this.player[num] = new Player(num + 1);
        this.teamScore = this.teamWicketsDown = 0;
        this.currentPlayer = 0;
    }

    public void showTeamScoreBoard(int teamNumber) {
        System.out.println("Team " + teamNumber + " stats: " + this.teamScore + "/" + this.teamWicketsDown);
        for(int num = 0; num < 11; num++)
            System.out.println(player[num].getPlayerDetails());
    }

    public int getTeamWicketsDown() {
        return this.teamWicketsDown;
    }

    public int getTeamScore() {
        return this.teamScore;
    }

    public void setTeamWicketsDown() {
        player[currentPlayer].setPlayerOut();
        currentPlayer++;
        this.teamWicketsDown++;
    }

    public void registerRuns(int runs) {
        teamScore += runs;
        player[currentPlayer].setRunsScored(runs);
    }
}