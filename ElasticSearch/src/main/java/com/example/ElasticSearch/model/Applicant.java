package com.example.ElasticSearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "applicant")
@Data
@NoArgsConstructor
@Setting(settingPath = "/settings/settings.json")
public class Applicant {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String firstname;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String lastname;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Text)
    private String address;
    @GeoPointField
    private GeoPoint location;
    @Field(type = FieldType.Text)
    private String phone;
    @Field(type = FieldType.Text)
    private String education;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String cvContent;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String clContent;

}
