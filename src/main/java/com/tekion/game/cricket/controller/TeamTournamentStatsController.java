package com.tekion.game.cricket.controller;

import com.tekion.game.cricket.service.TeamTournamentStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/team")
public class TeamTournamentStatsController {

    @Autowired
    private TeamTournamentStatsService teamTournamentStatsService;

    @GetMapping(path = "/num-wins/{teamName}")
    public Integer getNumOfWins(@PathVariable String teamName) {
      return teamTournamentStatsService.getNumOfWins(teamName);
    }
}