package com.example.ElasticSearch.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LocationDTO {
    private String city;
    private Double radius;
}
