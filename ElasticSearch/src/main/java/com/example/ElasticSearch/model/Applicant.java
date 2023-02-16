package com.example.ElasticSearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "applicant")
@Data
@NoArgsConstructor
public class Applicant {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "english", analyzer = "english", store = true)
    private String firstname;
    @Field(type = FieldType.Text, searchAnalyzer = "english", analyzer = "english", store = true)
    private String lastname;
    @Field(type = FieldType.Text, index = false, store = true)
    private String email;
    @Field(type = FieldType.Text, index = false, store = true)
    private String address;
    @GeoPointField
    private GeoPoint location;
    @Field(type = FieldType.Text, index = false, store = true)
    private String phone;
    @Field(type = FieldType.Text, store = true)
    private String education;
}
