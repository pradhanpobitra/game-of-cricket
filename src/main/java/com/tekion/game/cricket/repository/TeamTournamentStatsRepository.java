package com.tekion.game.cricket.repository;

import com.tekion.game.cricket.beans.document.TeamTournamentStats;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamTournamentStatsRepository extends ElasticsearchRepository<TeamTournamentStats, Long> {
    TeamTournamentStats findByTeamName(final String teamName);
}
