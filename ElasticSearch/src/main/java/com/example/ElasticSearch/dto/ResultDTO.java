package com.example.ElasticSearch.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResultDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String education;
    private String address;
    private String phone;
    private String location;
    private String cv;
    private String coverLetter;
}
