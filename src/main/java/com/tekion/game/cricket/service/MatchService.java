package com.tekion.game.cricket.service;

import com.tekion.game.cricket.beans.pojo.Bowler;
import com.tekion.game.cricket.beans.pojo.Match;
import com.tekion.game.cricket.beans.pojo.Player;
import com.tekion.game.cricket.beans.pojo.Team;
import com.tekion.game.cricket.utils.MatchUtil;
import com.tekion.game.cricket.utils.TournamentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchStatsService matchStatsService;

    @Autowired
    private PlayerTournamentStatsService playerTournamentStatsService;

    @Autowired
    private TeamTournamentStatsService teamTournamentStatsService;

    public void playTournament(final Integer numOvers) {
        Match match;
        Team team1,team2;

        for(int team1Index = 0; team1Index < TournamentUtil.teamsList.size(); team1Index++) {
            for(int team2Index = team1Index + 1; team2Index < TournamentUtil.teamsList.size(); team2Index++) {
                team1 = TournamentUtil.teamsList.get(team1Index);
                team2 = TournamentUtil.teamsList.get(team2Index);
                team1.restoreDataFields();
                team2.restoreDataFields();
                match = new Match(numOvers,team1,team2);
                playAndStoreResultsLocally(match,team1,team2);
            }
        }
        saveAllDataInDB();
    }

    private void playAndStoreResultsLocally(final Match match,final Team team1,final Team team2) {
        match.setPlayingEleven();
        playBattingAndBowlingOfRespectively(match,team1,team2);
        match.setTargetScore(team1.getTeamScore() + 1);
        match.setTargetSet(true);
        playBattingAndBowlingOfRespectively(match,team2,team1);
        match.setHasMatchEnded(true);
        match.setWinningTeam();
        match.setManOfTheMatch();
        match.setRunsScoredByPlayers();

        matchStatsService.storeMatchStatsLocally(match,team1,team2);
        playerTournamentStatsService.savePlayersTournamentStatsLocally(match);
        teamTournamentStatsService.saveTeamsTournamentStatsLocally(match.getWinningTeam(),team1.getTeamName(),team2.getTeamName());
    }

    private void saveAllDataInDB() {
        matchStatsService.saveAllMatchStatsInDB();
        playerTournamentStatsService.saveAllPlayersTournamentStatsInDB();
        teamTournamentStatsService.saveAllTeamsTournamentStatsInDB();
    }

    private void playBattingAndBowlingOfRespectively(final Match match,final Team battingTeam,final Team bowlingTeam) {
        int remainingOvers = match.getNumOfOvers();

        while( oversAndWicketsLeft(remainingOvers,battingTeam) ) {
            final Bowler currentBowler = bowlingTeam.getCurrentBowler();
            playAnOver(match,battingTeam,currentBowler);
            bowlingTeam.setCurrentBowlerIndex((bowlingTeam.getCurrentBowlerIndex() + 1) % 5);
            remainingOvers--;
        }
    }

    private boolean oversAndWicketsLeft(int remainingOvers,final Team battingTeam) {
        return remainingOvers > 0 && battingTeam.getTeamWicketsLost() < 10 ? true : false;
    }

    private void playAnOver(final Match match,final Team battingTeam,final Bowler currentBowler) {
        int remainingBallsInOver = 6;
        int runsInCurrentOver = 0;

        while(remainingBallsInOver > 0) {
            final Player currentBatsman = battingTeam.getCurrentBatsman();
            double batsmanRating = currentBatsman.getPlayerRating();
            double bowlerRating = currentBowler.getPlayerRating();

            final String currentBallScore = MatchUtil.getCurrentBallScore(currentBatsman.getClass().getName(),batsmanRating,bowlerRating);

            if(currentBallScore == "W") {
                currentBowler.setNoOfWicketsTaken(currentBowler.getNoOfWicketsTaken() + 1);
                battingTeam.setTeamWicketsLost(battingTeam.getTeamWicketsLost() + 1);
                if(battingTeam.getTeamWicketsLost() == 10) break;
                battingTeam.setCurrentBatsmanIndex(battingTeam.getCurrentBatsmanIndex() + 1);
            }
            else {
                int runs = Integer.parseInt(currentBallScore);
                currentBowler.setRunsGiven(currentBowler.getRunsGiven() + runs);
                battingTeam.setTeamScore(battingTeam.getTeamScore() + runs);
                currentBatsman.setNoOfBallsPlayed(currentBatsman.getNoOfBallsPlayed() + 1);
                currentBatsman.setNoOfRunsScored(currentBatsman.getNoOfRunsScored() + runs);
                runsInCurrentOver += runs;
                if(runs == 4 || runs == 6) {
                    currentBatsman.setNoOfBoundaries(currentBatsman.getNoOfBoundaries() + 1);
                }
                if(match.isTargetSet() && battingTeam.getTeamScore() >= match.getTargetScore()) return;
            }
            remainingBallsInOver--;
        }

        if(runsInCurrentOver == 0 && remainingBallsInOver == 0) {
            currentBowler.setNoOfMaidenOversBalled(currentBowler.getNoOfMaidenOversBalled() + 1);
        }
        currentBowler.setNoOfOversBalled(currentBowler.getNoOfOversBalled() + 1);
    }
}
