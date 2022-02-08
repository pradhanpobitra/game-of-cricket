package cricket;

class Match {
    private final int numOvers;
    private final Team team1 , team2;

    public Match(int numOvers) {
        team1 = new Team();
        team2 = new Team();
        this.numOvers = numOvers;
    }

    public void playAndShowResults() {
        makeTeamPlay(team1,numOvers);
        makeTeamPlay(team2,numOvers);
        showResults();
    }

    private void makeTeamPlay(Team team,int numOvers) {
        int numOfBalls = numOvers * 6;

        while( checkBallsAndWicketsLeft(team,numOfBalls) ) {
            checkBallOutcomeAndAct(team);
            numOfBalls--;
        }
    }
    private void showResults() {
        int team1Score = team1.getTeamScore();
        int team2Score = team2.getTeamScore();

        if(team1Score == team2Score) {
            System.out.println("Match tied");
        }
        else if(team1Score > team2Score) {
            System.out.println("Team 1 won");
        }
        else {
            System.out.println("Team 2 won");
        }

        System.out.println("Below are the stats: ");
        team1.showTeamScoreBoard(1);
        team2.showTeamScoreBoard(2);
    }

    private boolean checkBallsAndWicketsLeft(Team team,int numOfBalls) {
        return numOfBalls > 0 && team.getNumOfWicketsDown() < 10 ? true : false;
    }

    private void checkBallOutcomeAndAct(Team team) {
        String ballOutcome = MatchUtil.getBallOutcome();
        if(ballOutcome == "W") {
            team.increamentWickets();
            team.setCurrentPlayerOut();
            team.putNextPlayerOnStrike();
        }
        else {
            Integer runsScored = Integer.parseInt(ballOutcome);
            team.increamentScoreOfTeam(runsScored);
            team.increamentScoreOfCurrentPlayer(runsScored);
        }
    }
}
