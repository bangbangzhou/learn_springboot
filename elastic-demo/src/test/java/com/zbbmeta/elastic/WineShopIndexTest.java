package com.zbbmeta.elastic;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.elastic.entity.WineShop;
import com.zbbmeta.elastic.entity.WineShopDoc;
import com.zbbmeta.elastic.mapper.WineShopMapper;

import com.zbbmeta.elastic.service.WineShopService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static com.zbbmeta.elastic.constants.WineShopConstants.MAPPING_TEMPLATE;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */

@SpringBootTest
public class WineShopIndexTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void testInit() {
       System.out.println(this.client);
    }


    /**
     * 创建wineshop索引库
     */
    @Test
    public void createHotelIndex() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("wineshop");
        // 2.准备请求的参数：DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }


    /**
     *
     *删除wineshop索引库
     */
    @Test
    void testDeleteWineShopIndex() throws IOException {
        // 1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("wineshop");
        // 2.发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /**
     *
     * 判断ES索引库WineShop是否存在
     */
    @Test
    void testExistsWineShopIndex() throws IOException {
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("wineshop");
        // 2.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        // 3.输出
        System.err.println(exists ? "索引库已经存在！" : "索引库不存在！");
    }

    @Autowired
    private WineShopService wineShopService;

    /**
     *
     *新增WineShop下的文档数据
     */
    @Test
    void testAddWineShopDocument() throws IOException {
        // 1.根据id查询酒店数据
        WineShop wineShop = wineShopService.getById(61083L);
        // 2.转换为文档类型
        WineShopDoc wineShopDoc = new WineShopDoc(wineShop);
        // 3.将WineShopDoc转json
        String json = JSON.toJSONString(wineShopDoc);

        // 1.准备Request对象
        IndexRequest request = new IndexRequest("wineshop").id(wineShopDoc.getId().toString());
        // 2.准备Json文档
        request.source(json, XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }




    /**
     *
     * 查询WineShop下的id=61083文档数据
     */
    @Test
    void testGetWineShopDocumentById() throws IOException {
        // 1.准备Request
        GetRequest request = new GetRequest("wineshop", "61083");
        // 2.发送请求，得到响应
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.解析响应结果
        String json = response.getSourceAsString();

        WineShopDoc wineShopDoc = JSON.parseObject(json, WineShopDoc.class);
        System.out.println(wineShopDoc);
    }


    /**
     * 删除WineShop下的id=61083文档数据
     */
    @Test
    void testDeleteWineShopDocument() throws IOException {
        // 1.准备Request
        DeleteRequest request = new DeleteRequest("wineshop", "61083");
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testUpdateWineShopDocument() throws IOException {
        // 1.准备Request
        UpdateRequest request = new UpdateRequest("wineshop", "61083");
        // 2.准备请求参数
        request.doc(
                "price", "952",
                "starName", "四钻"
        );
        // 3.发送请求
        client.update(request, RequestOptions.DEFAULT);
    }

    /**
     *
     * 批量导入wineShop文档
     */
    @Test
    void testBulkRequest() throws IOException {
        // 批量查询酒店数据
        List<WineShop> wineShops = wineShopService.list();

        // 1.创建Request
        BulkRequest request = new BulkRequest();
        // 2.准备参数，添加多个新增的Request
        for (WineShop wineShop : wineShops) {
            // 2.1.转换为文档类型wineShopDoc
            WineShopDoc hotelDoc = new WineShopDoc(wineShop);
            // 2.2.创建新增文档的Request对象
            request.add(new IndexRequest("wineshop")
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }
}