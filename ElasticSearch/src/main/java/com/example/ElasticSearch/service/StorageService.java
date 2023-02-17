package com.example.ElasticSearch.service;

import com.example.ElasticSearch.model.Applicant;
import com.example.ElasticSearch.repository.ApplicantRepository;
import com.example.ElasticSearch.repository.CVRepository;
import com.example.ElasticSearch.repository.CoverLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final ApplicantRepository applicantRepository;
    private final CVRepository cvRepository;
    private final CoverLetterRepository coverLetterRepository;

    public void saveApplicant(Applicant applicant){
        applicantRepository.save(applicant);
    }

    public Applicant get( String id) {
        return applicantRepository.findById(id).orElse(null);
    }
}
