package com.cricketgame.controller;

import com.cricketgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/api/player/runs-scored-in-tournament/{playerName}")
    public String getRunsScoredByPlayerInTournament(@PathVariable String playerName) {
        playerName = playerName.toUpperCase();
        return playerService.getRunsScoredByPlayerInTournament(playerName);
    }

    @GetMapping("/api/player/matches-player-was-man-of-match/{playerName}")
    public ArrayList<String> getMatchesPlayerWasMOTM(@PathVariable String playerName) {
        return playerService.getMatchesPlayerGotMOTM(playerName);
    }
}
