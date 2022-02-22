package com.cricketgame.controller;

import com.cricketgame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{numOvers}")
    public void playNewGame(@PathVariable String numOvers) {
        matchService.playNewGame(Integer.parseInt(numOvers));
        return;
    }

    @GetMapping("/api/match/runs-scored-by-player/{playerName}/{matchId}")
    public String getRunsScoredByPlayerInMatch(@PathVariable String playerName, @PathVariable String matchId) {
        return matchService.getRunsScoredByPlayerInMatch(playerName,Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/man-of-the-match/{matchId}")
    public String getManOfTheMatchWithMatchId(@PathVariable String matchId) {
        return matchService.getManOfTheMatchWithMatchId(Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/details/{matchId}")
    public String getMatchDetailsWithMatchId(@PathVariable String matchId) {
        return matchService.getMatchStatsWithMatchId(Integer.parseInt(matchId));
    }

    @GetMapping("/api/match/playing-eleven/{matchId}")
    public ArrayList<String> getPlayingElevenOfMatch(@PathVariable String matchId) {
        return matchService.getPlayingElevenOfMatch(Integer.parseInt(matchId));
    }
}
