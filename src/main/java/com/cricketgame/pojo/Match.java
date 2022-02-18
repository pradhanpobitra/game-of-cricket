package com.cricketgame.pojo;

import com.cricketgame.repository.MatchStatsRepo;
import com.cricketgame.repository.TeamList;
import java.util.ArrayList;
import java.util.HashMap;

public class Match {
    private static int matchIdCount = 0;
    private final HashMap<String, Player> playerStats = new HashMap<>();
    private final Team team1, team2;
    private final int matchId;
    private final int numOfOvers;
    private int targetScore;
    private boolean isTargetSet;
    private boolean hasMatchEnded;
    private Player manOfTheMatch;
    private Team winningTeam;

    public Match(int numOfOvers) {
        this.numOfOvers = numOfOvers;
        matchId = ++matchIdCount;
        team1 = new Team(TeamList.teams.get(0));
        team2 = new Team(TeamList.teams.get(1));
    }

    public int getMatchId() {
        return matchId;
    }

    public String getWinningTeamName() {
        return winningTeam.getTeamName();
    }

    public String getRunsScoredByPlayer(String playerName) {
        if( ! playerStats.containsKey(playerName) ) {
            return "No such player played in this match.";
        }
        Integer runsScoredByPlayer = playerStats.get(playerName).getNoOfRunsScored();
        return runsScoredByPlayer.toString();
    }

    public void playAndShowResults() {
        playBattingAndBowlingOfRespectively(team1,team2);
        targetScore = team1.getTeamScore() + 1;
        isTargetSet = true;
        playBattingAndBowlingOfRespectively(team2,team1);
        hasMatchEnded = true;

        storeMatchStats();
        team1.updateTeamPlayersStats();
        team2.updateTeamPlayersStats();

        evaluateManOfTheMatch();
        showResults();
        if(winningTeam != null) {
            winningTeam.increamentTeamWins();
        }
        storeAllPlayerStats();
    }

    public void evaluateManOfTheMatch() {
        Player highestScorerTeam1 = team1.getHighestScorer();
        Player highestScorerTeam2 = team2.getHighestScorer();

        manOfTheMatch = ( highestScorerTeam1.noOfRunsScored > highestScorerTeam2.noOfRunsScored ? highestScorerTeam1 : highestScorerTeam2 );
        String nameOfManOfTheMatch = manOfTheMatch.getPlayerName();
        MatchStatsRepo.storeMOTM(nameOfManOfTheMatch,this);
    }

    public String getManOfTheMatch() {
        return manOfTheMatch.getPlayerName();
    }

    public ArrayList<String> getAllPlayers() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(team1.getAllPlayers());
        list.addAll(team2.getAllPlayers());
        return list;
    }

    private void storeAllPlayerStats() {
        team1.storePlayerStatsInTeam(playerStats);
        team2.storePlayerStatsInTeam(playerStats);
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
        MatchStatsRepo.storeMatchStats(matchId,this);
    }

    private void showResults() {
        int team1score = team1.getTeamScore();
        int team2score = team2.getTeamScore();

        if(team1score == team2score) {
            winningTeam = null;
            System.out.println("Match tied");
        }
        else if(team1score > team2score){
            winningTeam = team1;
            System.out.println("Team " + team1.getTeamName() + " has won the match.");
        }
        else {
            winningTeam = team2;
            System.out.println("Team " + team2.getTeamName() + " has won the match.");
        }

        team1.showTeamScoreBoard();
        team2.showTeamScoreBoard();
        System.out.println(getManOfTheMatch());
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

    @Override
    public String toString() {
        return "Match{" +
                "team1=" + team1.getTeamName() +
                ", team2=" + team2.getTeamName() +
                ", matchId=" + matchId +
                ", numOfOvers=" + numOfOvers +
                ", targetScore=" + targetScore +
                ", hasMatchEnded=" + hasMatchEnded +
                ", manOfTheMatch=" + manOfTheMatch.getPlayerName() +
                ", winningTeam=" + winningTeam.getTeamName() +
                '}';
    }
}
