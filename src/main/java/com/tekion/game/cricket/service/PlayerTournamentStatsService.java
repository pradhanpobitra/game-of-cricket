package com.tekion.game.cricket.service;

import com.tekion.game.cricket.beans.document.MatchStats;
import com.tekion.game.cricket.beans.document.PlayerTournamentStats;
import com.tekion.game.cricket.beans.pojo.Match;
import com.tekion.game.cricket.repository.PlayerTournamentStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerTournamentStatsService {

    @Autowired
    private PlayerTournamentStatsRepository playerTournamentStatsRepository;

    @Autowired
    private MatchStatsService matchStatsService;
    private final Map<String,PlayerTournamentStats> allPlayersTournamentStats = new HashMap<>();

    public Integer getRunsScoredInTournament(final String playerName) {
        return playerTournamentStatsRepository.findByPlayerName(playerName).getRunsScoredInTournament();
    }

    public List<MatchStats> getMatchesPlayerGotMOTM(final String playerName) {
        final List<Integer> matchIds = playerTournamentStatsRepository.findByPlayerName(playerName).getMatchesPlayerGotManOfTheMatch();
        final List<MatchStats> matches = new ArrayList<>();
        for(final Integer id : matchIds) {
            matches.add(matchStatsService.findByMatchId(id));
        }
        return matches;
    }

    public void savePlayersTournamentStatsLocally(final Match match) {
        PlayerTournamentStats playerTournamentStats;
        final Map<String,Integer> allPlayerMatchStats = match.getRunsScoredByPlayers();
        for(final String playerName : allPlayerMatchStats.keySet()) {
            updatePlayerInfoInAllPlayerTournamentStatsMap(playerName,allPlayerMatchStats);
        }
        final String manOfTheMatch = match.getManOfTheMatch().getPlayerName();
        allPlayersTournamentStats.get(manOfTheMatch).getMatchesPlayerGotManOfTheMatch().add(match.getMatchId());
    }

    public void saveAllPlayersTournamentStatsInDB() {
        for(final String playerName : allPlayersTournamentStats.keySet()) {
            playerTournamentStatsRepository.save(allPlayersTournamentStats.get(playerName));
        }
    }

    private void updatePlayerInfoInAllPlayerTournamentStatsMap(final String playerName,final Map<String,Integer> allPlayerMatchStats) {
        PlayerTournamentStats playerTournamentStats;
        if( ! allPlayersTournamentStats.containsKey(playerName) ) {
            playerTournamentStats = new PlayerTournamentStats();
            playerTournamentStats.setPlayerName(playerName).setRunsScoredInTournament(allPlayerMatchStats.get(playerName));
            final List<Integer> matchesPlayerGotMOTM = new ArrayList<>();
            playerTournamentStats.setMatchesPlayerGotManOfTheMatch((ArrayList<Integer>) matchesPlayerGotMOTM);
        }
        else {
            playerTournamentStats = allPlayersTournamentStats.get(playerName);
            playerTournamentStats.setRunsScoredInTournament(allPlayerMatchStats.get(playerName) + playerTournamentStats.getRunsScoredInTournament());
        }
        allPlayersTournamentStats.put(playerName,playerTournamentStats);
    }
}
