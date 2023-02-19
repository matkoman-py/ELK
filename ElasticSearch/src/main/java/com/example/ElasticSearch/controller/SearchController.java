package com.example.ElasticSearch.controller;


import com.example.ElasticSearch.dto.AdvancedQueryDTO;
import com.example.ElasticSearch.dto.LocationDTO;
import com.example.ElasticSearch.dto.ResultDTO;
import com.example.ElasticSearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@CrossOrigin
public class SearchController {

    private final SearchService searchService;

    @GetMapping("simple/{field}/{value}")
    public ResponseEntity<List<ResultDTO>> simpleQuery(@PathVariable(name = "field") String field,
                                                          @PathVariable(name = "value") String value) {
        return new ResponseEntity<>(searchService.simpleQuery(field, value), HttpStatus.OK);
    }

    @PostMapping("/advanced")
    public ResponseEntity<List<ResultDTO>> advancedQuery(@RequestBody AdvancedQueryDTO request){
        return new ResponseEntity<>(searchService.advancedQuery(request), HttpStatus.OK);
    }

    @PostMapping("/location")
    public ResponseEntity<List<ResultDTO>> queryLocation(@RequestBody LocationDTO request){
        return new ResponseEntity<>(searchService.queryLocation(request), HttpStatus.OK);
    }


}
