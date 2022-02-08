package cricket;

class Team {
    private int score;
    private int numOfWicketsDown;
    private int currentPlayerIndex;
    private Player[] player;

    public Team() {
        player = new Player[MatchUtil.NUM_PLAYERS];
        for(int num = 1; num <= MatchUtil.NUM_PLAYERS; num++) player[num - 1] = new Player(num);
    }

    public int getNumOfWicketsDown() {
        return numOfWicketsDown;
    }

    public int getTeamScore() {
        return score;
    }

    public void increamentWickets() {
        numOfWicketsDown++;
    }

    public void setCurrentPlayerOut() {
        player[currentPlayerIndex].setPlayerOut();
    }

    public void putNextPlayerOnStrike() {
        currentPlayerIndex++;
    }

    public void showTeamScoreBoard(int teamNumber) {
        System.out.println("Team " + teamNumber + " stats: " + score + "/" + numOfWicketsDown);

        for(int num = 0; num < MatchUtil.NUM_PLAYERS; num++) {
            System.out.println(player[num].getPlayerDetails());
        }
    }

    public void increamentScoreOfTeam(int runs) {
        score += runs;
    }

    public void increamentScoreOfCurrentPlayer(int runs) {
        player[currentPlayerIndex].increamentRunsScored(runs);
    }
}
