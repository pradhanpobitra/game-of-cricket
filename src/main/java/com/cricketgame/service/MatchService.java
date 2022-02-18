package com.cricketgame.service;

import com.cricketgame.pojo.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchService {
    public void startNewGame(Integer numOvers) {
        Match match = new Match(numOvers);
        match.playAndShowResults();
        return;
    }
}
