package com.tekion.game.cricket.repository;

import com.tekion.game.cricket.beans.document.PlayerTournamentStats;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTournamentStatsRepository extends ElasticsearchRepository<PlayerTournamentStats,String> {
    PlayerTournamentStats findByPlayerName(final String playerName);
}
