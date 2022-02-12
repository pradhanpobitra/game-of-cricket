package cricket;

import java.util.HashMap;

class Match {
    private static final HashMap<Integer,Match> matchStats = new HashMap<Integer,Match>();
    private static int matchIdCount;
    private final Team team1, team2;
    private final int matchId;
    private final int numOfOvers;
    private int targetScore;
    private boolean isTargetSet;

    public static void showMatchStatsWithMatchId(int matchId) {
        Match match = matchStats.get(matchId);
        if(match == null) {
            System.out.println("No match found with the given ID");
            return;
        }
        match.showResults();
    }

    public Match(int numOfOvers) {
        this.numOfOvers = numOfOvers;
        matchId = ++matchIdCount;
        team1 = new Team.TeamBuilder().setTeamName().selectSixBatsmen().selectFiveBowlers().build();
        team2 = new Team.TeamBuilder().setTeamName().selectSixBatsmen().selectFiveBowlers().build();
    }

    public void playAndShowResults() {
        playBattingAndBowlingOfRespectively(team1,team2);
        targetScore = team1.getTeamScore() + 1;
        isTargetSet = true;
        playBattingAndBowlingOfRespectively(team2,team1);
        storeMatchStats();
        team1.updateTeamPlayersStats();
        team2.updateTeamPlayersStats();

        showResults();
    }

    private void playBattingAndBowlingOfRespectively(Team battingTeam,Team bowlingTeam) {
        int remainingOvers = numOfOvers;

        while( oversAndWicketsLeft(remainingOvers,battingTeam) ) {
            Bowler currentBowler = bowlingTeam.getCurrentBowler();
            playAnOver(battingTeam,currentBowler);
            remainingOvers--;
        }
    }

    private void storeMatchStats() {
        matchStats.put(matchId,this);
    }

    private void showResults() {
        int team1score = team1.getTeamScore();
        int team2score = team2.getTeamScore();
        if(team1score == team2score) {
            System.out.println("Match tied");
        }
        else if(team1score > team2score) {
            System.out.println("Team " + team1.getTeamName() + " won the match.");
        }
        else {
            System.out.println("Team " + team2.getTeamName() + " won the match.");
        }

        team1.showTeamScoreBoard();
        team2.showTeamScoreBoard();
    }

    private boolean oversAndWicketsLeft(int remainingOvers,Team battingTeam) {
        return remainingOvers > 0 && battingTeam.getNumOfWicketsLost() < 10 ? true : false;
    }

    private void playAnOver(Team battingTeam,Bowler currentBowler) {
        int remainingBallsInOver = 6;
        int runsInCurrentOver = 0;

        while(remainingBallsInOver > 0) {
            Player currentBatsman = battingTeam.getCurrentBatsman();
            double batsmanRating = currentBatsman.getPlayerRating();
            double bowlerRating = currentBowler.getPlayerRating();

            String currentBallScore = MatchUtil.getCurrentBallScore(currentBatsman.getClass().getName(),batsmanRating,bowlerRating);

            if(currentBallScore == "W") {
                currentBowler.increamentNumWicketsTaken();
                battingTeam.increamentWicketsLost();
                if(battingTeam.getNumOfWicketsLost() == 10) break;
                battingTeam.setNextBatsmanOnStrike();
            }
            else {
                int runs = Integer.parseInt(currentBallScore);
                currentBowler.increamentRunsGiven(runs);
                runsInCurrentOver += runs;
                battingTeam.increamentTeamScore(runs);
                currentBatsman.increamentNumBallsPlayed();
                currentBatsman.increamentNumRunsScored(runs);
                if(runs == 4 || runs == 6) {
                    currentBatsman.increamentNumBoundaries();
                }
                if(isTargetSet && battingTeam.getTeamScore() >= targetScore) return;
            }
            remainingBallsInOver--;
        }

        if(runsInCurrentOver == 0 && remainingBallsInOver == 0) {
            currentBowler.increamentMaidenOversBowled();
        }
        currentBowler.increamentNumOversBalled();
    }
}
