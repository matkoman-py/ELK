package com.example.ElasticSearch.repository;

import com.example.ElasticSearch.model.CoverLetter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CoverLetterRepository extends ElasticsearchRepository<CoverLetter, String> {
}
