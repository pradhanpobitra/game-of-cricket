package cricket;

class MatchController {
    private int playingTeamNumber;
    private int numOvers;
    private Team[] team;
    public MatchController(Team teamZero,Team teamOne,int numOvers) {
        this.playingTeamNumber = 0;
        this.numOvers = numOvers;
        team = new Team[2];
        team[0] = teamZero;
        team[1] = teamOne;
    }
    public void controlGame() {
        while(this.playingTeamNumber < 2) startNewInnings();
        printResults();
    }

    private void startNewInnings() {
        int numOfBalls = this.numOvers * 6;

        while(checkBallsAndWicketsLeft(numOfBalls)) {
            bowlBall();
            numOfBalls--;
        }
        this.playingTeamNumber++;
    }

    private void bowlBall() {
        int ballOutcome = (int) Math.floor(Math.random() * 8);
        if(ballOutcome == 7)  // player got out
            team[playingTeamNumber].setTeamWicketsDown();
        else team[playingTeamNumber].increamentScore(ballOutcome);
    }

    private boolean checkBallsAndWicketsLeft(int numOfBalls) {
        return numOfBalls > 0 && team[playingTeamNumber].getTeamWicketsDown() < 10 ? true : false;
    }

    private void printResults() {
        int teamZeroScore = team[0].getTeamScore();
        int teamOneScore = team[1].getTeamScore();
        if(teamOneScore == teamZeroScore) System.out.println("Match tied");
        else {
            if (teamZeroScore > teamOneScore) {
                System.out.println("Team 1 won");
            } else {
                System.out.println("Team 2 won");
            }
        }
        System.out.println("Below are the stats: ");
        team[0].showTeamScoreBoard(1);
        team[1].showTeamScoreBoard(2);
    }
}
