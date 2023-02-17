package com.example.ElasticSearch.repository;

import com.example.ElasticSearch.model.Applicant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ApplicantRepository extends ElasticsearchRepository<Applicant, String> {
}
