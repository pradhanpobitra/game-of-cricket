package com.cricketgame.service;

import com.cricketgame.repository.MatchStatsRepo;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class MatchStatsService {
    public String getRunsScoredByPlayerInMatch(String playerName, int matchId) {
        return MatchStatsRepo.getRunsScoredByPlayerInMatch(playerName,matchId);
    }

    public String getManOfTheMatchWithMatchId(int matchId) {
        return MatchStatsRepo.getManOfTheMatchWithMatchId(matchId);
    }

    public String getMatchStatsWithMatchId(int matchId) {
        return MatchStatsRepo.getMatchStatsWithMatchId(matchId);
    }

    public ArrayList<String> getMatchesPlayerGotMOTM(String playerName) {
        return MatchStatsRepo.getMatchesPlayerGotMOTM(playerName);
    }

    public ArrayList<String> getPlayingElevenOfMatch(int matchId) {
        return MatchStatsRepo.getPlayingElevenOfMatch(matchId);
    }
}
