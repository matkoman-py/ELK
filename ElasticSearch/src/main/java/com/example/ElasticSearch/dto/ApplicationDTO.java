package com.example.ElasticSearch.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplicationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String education;
    private String address;
    private String phone;
    private Double lat;
    private Double lon;
    private MultipartFile cv;
    private MultipartFile coverLetter;
}
