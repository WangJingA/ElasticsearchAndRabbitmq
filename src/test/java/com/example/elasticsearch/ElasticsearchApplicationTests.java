package com.example.elasticsearch;

import com.example.elasticsearch.DO.Teacher;
import com.example.elasticsearch.Reposity.EsTeacherMapper;
import com.example.elasticsearch.config.RestHightClient;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Optional;


@SpringBootTest
class ElasticsearchApplicationTests {

    @Resource
    RestHightClient restHightClient;
    @Autowired
    EsTeacherMapper teacherMapper;

    @Test
    void contextLoads3() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("teacher");
        String mapping = "{\n"+
                            "\"properties\":{\n"+
                                "\"id\":{" +
                                "\"type\":\"keyword\"\n" +
                                "},\n"+
                                "\"teacherName\":{\"type\":\"text\"},\n"+
                                "\"email\":{\"type\":\"text\"},\n"+
                                "\"address\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\n"+
                                "\"phone\":{\"type\":\"text\"}\n"+
                            "}\n"+
                            "}";
        createIndexRequest.mapping(mapping, XContentType.JSON);
        CreateIndexResponse createIndexResponse = restHightClient.elasticsearchClient()
                .indices()
                .create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("创建状态："+createIndexResponse.isAcknowledged());
    }

    @Test
    public void deleteIndex() throws IOException {
        AcknowledgedResponse teacher = restHightClient.elasticsearchClient()
                .indices()
                .delete(new DeleteIndexRequest()
                        .indices("teacher"), RequestOptions.DEFAULT);
        System.out.println("delete callBack:"+teacher.isAcknowledged());
    }
    @Test
    public void PutData(){
        Teacher teacher = new Teacher();
        teacher.setId("A00003");
        teacher.setTeacherName("陈平安");
        teacher.setPhone("34546565265");
        teacher.setEmail("2435454656@qq.com");
        teacher.setAddress("大丽龙泉县落魄山竹楼");
        Teacher save = teacherMapper.save(teacher);
        System.out.println(save);
    }

    @Test
    public void query() throws IOException {
        /**
         * 依靠id查询
         */
        Optional<Teacher> a00001 = teacherMapper.findById("A00002");
        System.out.println(a00001.get());
        /**
         * 查询所有
         */
        Iterable<Teacher> all = teacherMapper.findAll();
        all.forEach(teacher -> System.out.println(teacher));
        /**
         * 条件查询
         */
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("竹楼", "address");
        SearchRequest searchRequest = new SearchRequest("teacher");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHightClient.elasticsearchClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit: hits) {
            System.out.println(hit.getIndex()+":"+hit.getSourceAsString());
        }
    }
    @Test
    public void filterQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("teacher");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("address","解放"))
                .postFilter(QueryBuilders.idsQuery().addIds("A00001"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHightClient.elasticsearchClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit showHit:hits) {
            System.out.println(showHit.getSourceAsString());
        }
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","解放"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHightClient.elasticsearchClient().search(searchRequest,RequestOptions.DEFAULT);
        SearchHit[] hits1 = response.getHits().getHits();
        for (SearchHit searchHit: hits1) {
            System.out.println(searchHit.getSourceAsString());
        }
    }
}
