package com.example.ElasticSearch.repository;

import com.example.ElasticSearch.model.CV;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CVRepository extends ElasticsearchRepository<CV, String> {
}
