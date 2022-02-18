package com.cricketgame.service;

import com.cricketgame.repository.PlayerRepo;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {
    public String getRunsScoredByPlayerInTournament(String playerName) {
        return PlayerRepo.getRunsScoredByPlayerInTournament(playerName);
    }
}
