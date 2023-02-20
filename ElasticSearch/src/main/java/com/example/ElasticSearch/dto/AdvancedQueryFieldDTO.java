package com.example.ElasticSearch.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdvancedQueryFieldDTO {
    private String field;
    private String value;
    private String operator;
}
