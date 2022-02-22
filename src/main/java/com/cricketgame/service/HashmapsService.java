package com.cricketgame.service;

import com.cricketgame.document.Hashmaps;
import com.cricketgame.document.Player;
import com.cricketgame.repository.HashmapsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashmapsService {

    @Autowired
    private HashmapsRepo hashmapsRepo;

    public void updatePlayerTournamentStats(String playerName, Player player) {
        Hashmaps hashmaps = hashmapsRepo.findById("1").get();
        hashmaps.updatePlayerTournamentStats(playerName, player);
    }
}
