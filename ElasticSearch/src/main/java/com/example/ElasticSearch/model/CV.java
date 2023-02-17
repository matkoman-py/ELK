package com.example.ElasticSearch.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "cv")
@Data
@NoArgsConstructor
public class CV {
    @Id
    private String id;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Applicant applicant;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian-analyzer", analyzer = "serbian-analyzer")
    private String content;

    @Field(type = FieldType.Keyword, index = false, store = true)
    private Long cvId;
}
