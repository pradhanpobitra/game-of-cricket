package com.cricketgame.repository;

import com.cricketgame.document.Hashmaps;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashmapsRepo extends ElasticsearchRepository<Hashmaps, String> {

}
