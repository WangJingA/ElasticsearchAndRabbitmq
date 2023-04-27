package com.example.elasticsearch.Reposity;

import com.example.elasticsearch.DO.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: zhouwenjie
 * @description:
 * @create: 2022-05-12 17:37
 * ElasticsearchRepository<T, ID> T：实体类泛型，ID：实体类主键类型
 **/

public interface StudentMapper extends ElasticsearchRepository<Student, String> {
}

