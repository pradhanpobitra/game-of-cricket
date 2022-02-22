package com.cricketgame.service;

import com.cricketgame.helper.MatchUtil;
import com.cricketgame.document.*;
import com.cricketgame.pojo.Bowler;
import com.cricketgame.repository.HashmapsRepo;
import com.cricketgame.repository.MatchRepo;
import com.cricketgame.helper.TeamList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private HashmapsRepo hashmapsRepo;

    private Team team1 = TeamList.teams.get(0);
    private Team team2 = TeamList.teams.get(1);
    private Match match;


    public String getManOfTheMatchWithMatchId(int matchId) {
        Match match = matchRepo.findById(matchId).get();
        return match.getManOfTheMatch();
    }

    public String getMatchStatsWithMatchId(int matchId) {
        Match match = matchRepo.findById(matchId).get();
        return match.toString();
    }

    public String getRunsScoredByPlayerInMatch(String playerName, int matchId) {
        return matchRepo.findById(matchId).get().getRunsScoredByPlayer(playerName);
    }

    public ArrayList<String> getPlayingElevenOfMatch(int matchId) {
        return matchRepo.findById(matchId).get().getAllPlayers();
    }

    public void playNewGame(Integer numOvers) {
        match = new Match(numOvers, team1, team2);
        playAndShowResults();
        return;
    }

    private void playAndShowResults() {
        playBattingAndBowlingOfRespectively(team1,team2);
        match.setTargetScore(team1.getTeamScore() + 1);

        playBattingAndBowlingOfRespectively(team2,team1);
        match.setHasMatchEnded(true);

        storeMatchStats();
        team1.updateTeamPlayersStats();
        team2.updateTeamPlayersStats();

        evaluateManOfTheMatch();
        showResults();
        if(match.getWinningTeam() != null) {
            hashmapsRepo.findById("1").get().increamentNumTeamWins(match.getWinningTeam().getTeamName());
        }
        storeAllPlayerStats();
    }

    private void playBattingAndBowlingOfRespectively(Team battingTeam,Team bowlingTeam) {
        int remainingOvers = match.getNumOvers();

        while( oversAndWicketsLeft(remainingOvers,battingTeam) ) {
            Bowler currentBowler = bowlingTeam.getCurrentBowler();
            playAnOver(battingTeam,currentBowler);
            remainingOvers--;
        }
    }

    private void storeMatchStats() {
        matchRepo.save(match);
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
                if(match.getIsTargetSet() && battingTeam.getTeamScore() >= match.getTargetScore()) return;
            }
            remainingBallsInOver--;
        }

        if(runsInCurrentOver == 0 && remainingBallsInOver == 0) {
            currentBowler.increamentMaidenOversBowled();
        }
        currentBowler.increamentNumOversBalled();
    }

    private void evaluateManOfTheMatch() {
        Player highestScorerTeam1 = team1.getHighestScorer();
        Player highestScorerTeam2 = team2.getHighestScorer();

        Player manOfTheMatch = ( highestScorerTeam1.getNoOfRunsScored() > highestScorerTeam2.getNoOfRunsScored() ? highestScorerTeam1 : highestScorerTeam2 );
        match.setManOfTheMatch(manOfTheMatch);
        manOfTheMatch.addMatchToMOTMList(match);
    }

    private void showResults() {
        int team1score = team1.getTeamScore();
        int team2score = team2.getTeamScore();

        if(team1score == team2score) {
            match.setWinningTeam(null);
            System.out.println("Match tied");
        }
        else if(team1score > team2score){
            match.setWinningTeam(team1);
            System.out.println("Team " + team1.getTeamName() + " has won the match.");
        }
        else {
            match.setWinningTeam(team2);
            System.out.println("Team " + team2.getTeamName() + " has won the match.");
        }

        team1.showTeamScoreBoard();
        team2.showTeamScoreBoard();
        System.out.println(match.getManOfTheMatch());
    }

    private void storeAllPlayerStats() {
        team1.storePlayerStatsInTeam();
        team2.storePlayerStatsInTeam();
    }
}
