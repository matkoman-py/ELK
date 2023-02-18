package com.example.ElasticSearch.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApiResponseDTO {
    private String name;
    private Double latitude;
    private Double longitude;
    private String country;

}
