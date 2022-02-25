package com.tekion.game.cricket.service;

import com.tekion.game.cricket.beans.document.MatchStats;
import com.tekion.game.cricket.beans.pojo.Match;
import com.tekion.game.cricket.beans.pojo.Team;
import com.tekion.game.cricket.repository.MatchStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchStatsService {

    @Autowired
    private MatchStatsRepository matchStatsRepository;
    private final List<MatchStats> allMatchStats = new ArrayList<>();

    public Integer getRunsScoredByPlayerInMatch(final Integer matchId,final String playerName) {
        final MatchStats matchStats = matchStatsRepository.findByMatchId(matchId);
        final Map<String,Integer> runsScoredByPlayers = matchStats.getRunsScoredByPlayer();
        return runsScoredByPlayers.get(playerName);
    }

    public List<String> getPlayingEleven(final Integer matchId) {
        final MatchStats matchStats = matchStatsRepository.findByMatchId(matchId);
        return matchStats.getPlayingEleven();
    }

    public String getManOfTheMatch(final Integer matchId) {
        final MatchStats matchStats = matchStatsRepository.findByMatchId(matchId);
        return matchStats.getManOfTheMatch();
    }

    public MatchStats findByMatchId(final Integer matchId) {
        return matchStatsRepository.findByMatchId(matchId);
    }

    public void storeMatchStatsLocally(final Match match,final Team team1,final Team team2) {
        final MatchStats matchStats = new MatchStats();
        matchStats.setMatchId(match.getMatchId());
        matchStats.setRunsScoredByPlayer((HashMap<String, Integer>) match.getRunsScoredByPlayers());
        matchStats.setPlayingEleven((ArrayList<String>) match.getPlayingEleven());
        matchStats.setManOfTheMatch(match.getManOfTheMatch().getPlayerName());
        matchStats.setTeam1name(team1.getTeamName());
        matchStats.setTeam2name(team2.getTeamName());
        if(match.getWinningTeam() != null) {
            matchStats.setWinningTeamName(match.getWinningTeam().getTeamName());
        }
        allMatchStats.add(matchStats);
    }

    public void saveAllMatchStatsInDB() {
        matchStatsRepository.saveAll(allMatchStats);
    }
}
