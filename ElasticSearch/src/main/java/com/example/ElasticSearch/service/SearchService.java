package com.example.ElasticSearch.service;

import com.example.ElasticSearch.dto.*;
import com.example.ElasticSearch.model.Applicant;
import com.example.ElasticSearch.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ApplicantRepository applicantRepository;
    private final RestTemplate restTemplate;
    @Value("${API_KEY}")
    private String API_ACCESS_TOKEN;
    @Value("${API_URL}")
    private String API_URL;

    private QueryBuilder createMatchQueryBuilder(String field, String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return QueryBuilders.matchPhraseQuery(field, value);
        } else {
            return QueryBuilders.matchQuery(field, value);
        }
    }

    public List<ResultDTO> simpleQuery(String field, String value) {
        var searchQuery = new NativeSearchQueryBuilder()
                .withFilter(createMatchQueryBuilder(field, value))
                .build();

        SearchHits<Applicant> hints = elasticsearchOperations.search(searchQuery, Applicant.class, IndexCoordinates.of("applicant"));
        List<ResultDTO> found = new ArrayList<>();

        for (SearchHit<Applicant> hint : hints){
            Applicant applicant = hint.getContent();
            found.add(ResultDTO.builder()
                    .firstName(applicant.getFirstname())
                    .lastName(applicant.getLastname())
                    .phone(applicant.getPhone())
                    .education(applicant.getEducation())
                    .address(applicant.getAddress())
                    .email(applicant.getEmail())
                    .location(applicant.getLocation().toString())
                    .coverLetter(applicant.getClContent())
                    .cv(applicant.getCvContent())
                    .build());
        }

        return found;
    }

    public ApiResponseDTO getLocationFromAddress(String city) {
        var url = API_URL + city;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", API_ACCESS_TOKEN);

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ApiResponseDTO[]> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, ApiResponseDTO[].class);

            return Objects.requireNonNull(response.getBody())[0];
        }
        catch (Exception e){
            return null;
        }
    }

    public List<ResultDTO> queryLocation(LocationDTO request) {
        ApiResponseDTO apiResponseDTO = getLocationFromAddress(request.getCity());
        System.out.println(apiResponseDTO);

        var locationFilter = new GeoDistanceQueryBuilder("location")
                .point(apiResponseDTO.getLatitude(), apiResponseDTO.getLongitude())
                .distance(request.getRadius(), DistanceUnit.KILOMETERS);

        var query = new NativeSearchQueryBuilder()
                .withFilter(locationFilter)
                .build();

        var hints = elasticsearchOperations.search(query, Applicant.class, IndexCoordinates.of("applicant"));
        List<ResultDTO> found = new ArrayList<>();

        for (SearchHit<Applicant> hint : hints){
            Applicant applicant = hint.getContent();
            found.add(ResultDTO.builder()
                    .firstName(applicant.getFirstname())
                    .lastName(applicant.getLastname())
                    .phone(applicant.getPhone())
                    .education(applicant.getEducation())
                    .address(applicant.getAddress())
                    .email(applicant.getEmail())
                    .location(applicant.getLocation().toString())
                    .coverLetter(applicant.getClContent())
                    .cv(applicant.getCvContent())
                    .build());
        }

        return found;
    }

    public List<ResultDTO> advancedQuery(AdvancedQueryDTO request) {
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        for (AdvancedQueryFieldDTO field : request.getQuery()){
            QueryBuilder matcher = field.getPhrase()
                    ? QueryBuilders.matchPhraseQuery(field.getField(), field.getValue())
                    : QueryBuilders.matchQuery(field.getField(), field.getValue());

            if (field.getOperator().toLowerCase().equals("and")) {
                query.must(matcher);
            }
            else {
                query.should(matcher);
            }
        }

        var nativeQuery = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        var hints = elasticsearchOperations.search(nativeQuery, Applicant.class, IndexCoordinates.of("applicant"));
        List<ResultDTO> found = new ArrayList<>();

        for (SearchHit<Applicant> hint : hints){
            Applicant applicant = hint.getContent();
            found.add(ResultDTO.builder()
                    .firstName(applicant.getFirstname())
                    .lastName(applicant.getLastname())
                    .phone(applicant.getPhone())
                    .education(applicant.getEducation())
                    .address(applicant.getAddress())
                    .email(applicant.getEmail())
                    .location(applicant.getLocation().toString())
                    .coverLetter(applicant.getClContent())
                    .cv(applicant.getCvContent())
                    .build());
        }

        return found;
    }
}
