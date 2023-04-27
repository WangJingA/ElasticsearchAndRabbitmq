package com.example.elasticsearch.Reposity;

import com.example.elasticsearch.DO.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsTeacherMapper extends ElasticsearchRepository<Teacher,String> {
}
