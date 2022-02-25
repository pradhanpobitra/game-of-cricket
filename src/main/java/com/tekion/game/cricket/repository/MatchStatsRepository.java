package com.tekion.game.cricket.repository;

import com.tekion.game.cricket.beans.document.MatchStats;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatsRepository extends ElasticsearchRepository<MatchStats,String> {
    MatchStats findByMatchId(final Integer matchId);
}
