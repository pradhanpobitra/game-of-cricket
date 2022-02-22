package com.cricketgame.repository;

import com.cricketgame.document.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlayerRepo extends ElasticsearchRepository<Player, String> {

}
