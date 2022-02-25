package com.tekion.game.cricket.service;

import com.tekion.game.cricket.beans.document.TeamTournamentStats;
import com.tekion.game.cricket.beans.pojo.Team;
import com.tekion.game.cricket.repository.TeamTournamentStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeamTournamentStatsService {

    @Autowired
    private TeamTournamentStatsRepository teamTournamentStatsRepository;
    private final Map<String,TeamTournamentStats> allTeamsTournamentStats = new HashMap<>();

    public Integer getNumOfWins(final String teamName) {
        final TeamTournamentStats teamTournamentStats = teamTournamentStatsRepository.findByTeamName(teamName);
        return teamTournamentStats.getWinsCount();
    }

    public void saveTeamsTournamentStatsLocally(final Team winningTeam,final String team1name,final String team2name) {
        TeamTournamentStats teamTournamentStats;

        if (!allTeamsTournamentStats.containsKey(team1name)) {
            teamTournamentStats = new TeamTournamentStats();
            teamTournamentStats.setTeamName(team1name);
            teamTournamentStats.setWinsCount(0);
            allTeamsTournamentStats.put(team1name,teamTournamentStats);
        }

        if (!allTeamsTournamentStats.containsKey(team2name)) {
            teamTournamentStats = new TeamTournamentStats();
            teamTournamentStats.setTeamName(team2name);
            teamTournamentStats.setWinsCount(0);
            allTeamsTournamentStats.put(team2name,teamTournamentStats);
        }

        if(winningTeam == null) {
            return;
        }

        if(winningTeam.getTeamName().equals(team1name)) {
            teamTournamentStats = allTeamsTournamentStats.get(team1name);
            teamTournamentStats.setWinsCount(teamTournamentStats.getWinsCount() + 1);
        }
        else {
            teamTournamentStats = allTeamsTournamentStats.get(team2name);
            teamTournamentStats.setWinsCount(teamTournamentStats.getWinsCount() + 1);
        }
    }

    public void saveAllTeamsTournamentStatsInDB() {
        for(final String teamName : allTeamsTournamentStats.keySet()) {
            teamTournamentStatsRepository.save(allTeamsTournamentStats.get(teamName));
        }
    }
}
