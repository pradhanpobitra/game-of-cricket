package com.cricketgame.controller;

import com.cricketgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/api/num-of-team-wins/{teamName}")
    public String getNumOfTeamWins(@PathVariable String teamName) {
        return teamService.getNumTeamWins(teamName.toUpperCase());
    }
}
