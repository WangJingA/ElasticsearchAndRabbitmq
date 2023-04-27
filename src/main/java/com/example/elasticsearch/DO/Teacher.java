package com.example.elasticsearch.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "teacher",createIndex = true)
public class Teacher {
    @Id
    @Field(store = true,type = FieldType.Keyword)
    private String id;

    @Field(store = true,type = FieldType.Text)
    private String teacherName;

    @Field(store = true,type = FieldType.Text)
    private String email;

    @Field(store = true,type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    @Field(store = true,type = FieldType.Text)
    private String phone;

}
