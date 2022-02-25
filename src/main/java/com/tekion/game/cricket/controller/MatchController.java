package com.tekion.game.cricket.controller;

import com.tekion.game.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping(path = "/play-game/{numOvers}")
    public void playTournament(@PathVariable String numOvers) {
        matchService.playTournament(Integer.parseInt(numOvers));
    }
}
