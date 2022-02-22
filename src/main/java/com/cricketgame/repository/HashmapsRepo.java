package com.cricketgame.repository;

import com.cricketgame.document.Hashmaps;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HashmapsRepo extends ElasticsearchRepository<Hashmaps, String> {

}
