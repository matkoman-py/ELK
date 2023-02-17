package com.example.ElasticSearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "coverletter")
@Data
@NoArgsConstructor
public class CoverLetter {
    @Id
    private String id;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Applicant applicant;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String content;

    @Field(type = FieldType.Keyword, index = false, store = true)
    private Long coverLetterId;
}
