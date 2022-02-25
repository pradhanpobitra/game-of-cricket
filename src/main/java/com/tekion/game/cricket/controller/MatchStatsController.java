package com.tekion.game.cricket.controller;

import com.tekion.game.cricket.service.MatchStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api/match")
public class MatchStatsController {

    @Autowired
    private MatchStatsService matchStatsService;

    @GetMapping(path = "/runs-scored-by-player/{playerName}/{matchId}")
    public Integer getRunsScoredByPlayerInMatch(@PathVariable String playerName,@PathVariable String matchId) {
        return matchStatsService.getRunsScoredByPlayerInMatch(Integer.parseInt(matchId), playerName);
    }

    @GetMapping(path = "/playing-eleven/{matchId}")
    public List<String> getPlayingEleven(@PathVariable String matchId) {
        return matchStatsService.getPlayingEleven(Integer.parseInt(matchId));
    }

    @GetMapping(path = "/man-of-the-match/{matchId}")
    public String getManOfTheMatch(@PathVariable String matchId) {
        return matchStatsService.getManOfTheMatch(Integer.parseInt(matchId));
    }
}
