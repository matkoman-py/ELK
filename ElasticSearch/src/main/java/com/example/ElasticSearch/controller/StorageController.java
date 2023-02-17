package com.example.ElasticSearch.controller;

import com.example.ElasticSearch.model.Applicant;
import com.example.ElasticSearch.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/storage")
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public void apply(@RequestBody Applicant applicant){
        storageService.saveApplicant(applicant);
    }

    @GetMapping("/{id}")
    public Applicant apply(@PathVariable String id){
        return  storageService.get(id);
    }
}
