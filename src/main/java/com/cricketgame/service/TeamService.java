package com.cricketgame.service;

import com.cricketgame.repository.TeamRepo;
import org.springframework.stereotype.Component;

@Component
public class TeamService {
    public String getNumTeamWins(String teamName) {
        return TeamRepo.getNumOfTeamWins(teamName);
    }
}
