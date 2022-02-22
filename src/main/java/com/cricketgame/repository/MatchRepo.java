package com.cricketgame.repository;

import com.cricketgame.document.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepo extends ElasticsearchRepository<Match, Integer> {

}
