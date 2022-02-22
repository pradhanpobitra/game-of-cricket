package com.cricketgame.service;

import com.cricketgame.repository.HashmapsRepo;
import com.cricketgame.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private HashmapsRepo hashmapsRepo;

    public String getRunsScoredByPlayerInTournament(String playerName) {
        return hashmapsRepo.findById("1").get().getPlayerTournamentRuns(playerName);
    }

    public ArrayList<String> getMatchesPlayerGotMOTM(String playerName) {
        return playerRepo.findById(playerName).get().getMatchesPlayerWasMOTM();
    }
}
