package com.cricketgame.controller;

import com.cricketgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/api/player/runs-scored-in-tournament/")
    public String getRunsScoredByPlayerInTournament(@RequestParam String playerName) {
        playerName = playerName.toUpperCase();
        return playerService.getRunsScoredByPlayerInTournament(playerName);
    }
}
