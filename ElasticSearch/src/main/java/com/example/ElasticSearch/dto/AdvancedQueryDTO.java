package com.example.ElasticSearch.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdvancedQueryDTO {
    private List<AdvancedQueryFieldDTO> query;
}
