package com.cricketgame.repository;

import com.cricketgame.document.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends ElasticsearchRepository<Player, String> {

}
