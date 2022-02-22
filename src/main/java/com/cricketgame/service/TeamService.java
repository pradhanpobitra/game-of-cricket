package com.cricketgame.service;

import com.cricketgame.repository.HashmapsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private HashmapsRepo hashmapsRepo;

    public String getNumTeamWins(String teamName) {
        return hashmapsRepo.findById("1").get().getNumTeamWins(teamName);
    }
}
