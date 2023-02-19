package com.example.ElasticSearch.controller;

import com.example.ElasticSearch.dto.ApplicationDTO;
import com.example.ElasticSearch.model.Applicant;
import com.example.ElasticSearch.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/storage")
@CrossOrigin
public class StorageController {

    private final StorageService storageService;

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> apply(@ModelAttribute ApplicationDTO applicationDTO){
        storageService.handleApplication(applicationDTO);
        return new ResponseEntity<>("Succesfully uploaded!", HttpStatus.OK);
    }


}
