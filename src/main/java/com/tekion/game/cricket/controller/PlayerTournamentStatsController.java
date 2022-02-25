package com.tekion.game.cricket.controller;

import com.tekion.game.cricket.beans.document.MatchStats;
import com.tekion.game.cricket.service.PlayerTournamentStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api/player")
public class PlayerTournamentStatsController {

    @Autowired
    private PlayerTournamentStatsService playerTournamentStatsService;

    @GetMapping("/runs-scored-in-tournament/{playerName}")
    public Integer getRunsScoredInTournament(@PathVariable String playerName) {
        return playerTournamentStatsService.getRunsScoredInTournament(playerName);
    }

    @GetMapping("/matches-player-got-man-of-the-match/{playerName}")
    public List<MatchStats> getMatchesPlayerGotMOTM(@PathVariable String playerName) {
        return playerTournamentStatsService.getMatchesPlayerGotMOTM(playerName);
    }
}
