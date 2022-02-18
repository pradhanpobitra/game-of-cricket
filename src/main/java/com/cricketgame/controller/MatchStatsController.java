package com.cricketgame.controller;

import com.cricketgame.service.MatchStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MatchStatsController {
    private final MatchStatsService matchStatsService;

    @Autowired
    public MatchStatsController(MatchStatsService matchStatsService) {
        this.matchStatsService = matchStatsService;
    }

    @GetMapping("/api/match/runs-scored-by-player/{playerName}/{matchId}")
    public String getRunsScoredByPlayerInMatch(@PathVariable String playerName, @PathVariable String matchId) {
        return matchStatsService.getRunsScoredByPlayerInMatch(playerName,Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/man-of-the-match/{matchId}")
    public String getManOfTheMatchWithMatchId(@PathVariable String matchId) {
        return matchStatsService.getManOfTheMatchWithMatchId(Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/details/{matchId}")
    public String getMatchDetailsWithMatchId(@PathVariable String matchId) {
        return matchStatsService.getMatchStatsWithMatchId(Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/all-matches-player-got-MOTM/{playerName}")
    public ArrayList<String> getAllMatchesPlayerGotMOTM(@PathVariable String playerName) {
        String player = playerName.toUpperCase();
        return matchStatsService.getMatchesPlayerGotMOTM(player);
    }

    @GetMapping("/api/match/playing-eleven/{matchId}")
    public ArrayList<String> getPlayingElevenOfMatch(@PathVariable String matchId) {
        return matchStatsService.getPlayingElevenOfMatch(Integer.parseInt(matchId));
    }
}
