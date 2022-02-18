package com.cricketgame.controller;

import com.cricketgame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{numOvers}")
    public void startNewGame(@PathVariable String numOvers) {
        matchService.startNewGame(Integer.parseInt(numOvers));
        return;
    }
}
