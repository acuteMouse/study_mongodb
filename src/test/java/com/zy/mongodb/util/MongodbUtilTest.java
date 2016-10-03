package com.zy.mongodb.util;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

/**
 * @autor cgl
 * @time 2016/10/2.
 * 作用:
 */
public class MongodbUtilTest {
    MongoDatabase database = null;
    MongoCollection<Document> stud = null;
    Block<Document> printDo = document -> System.out.println(document.toJson());

    @Before
    public void testGetDefaultMongodbDataBase() {
        database = MongodbUtil.getDefaultMongodbDataBase("student");//数据库
        stud = database.getCollection("stud");
    }

    @Test
    public void testClose() {

    }

    /**
     * 查询所有
     */
    @Test
    public void findAll() {
        for (Document document : stud.find()) {
            System.out.println(document.toJson());
        }
    }

    @Test
    public void count() {
        Assert.assertEquals("不相等", 103, stud.count());
    }

    /**
     * 查询某个记录 eq  等于
     */
    @Test
    public void findOne() {
        Document document = stud.find(eq("i", 1)).first();
        System.out.println(document.toJson());
    }

    /**
     * gt  大于
     */
    @Test
    public void gtFind() {
        stud.find(gt("i", 60)).forEach(printDo);
    }


    /**
     * gt ---lt  大于---小于
     */
    @Test
    public void gtAndLt() {
        stud.find(and(gt("i", 50), lt("i", 60))).forEach(printDo);
    }

    /**
     * 插入一条记录，insertOne()  参数：doucument对象
     * 插入多条  inserMany()  参数：list<Document>
     */
    @Test
    public void insert() {
        Document document = new Document();
        document.put("name", "张三");
        document.append("age", 12);
        stud.insertOne(document);
        List<Document> documents = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }
        stud.insertMany(documents);
    }
    /**
     * 修改某个值
     */
    @Test
    public void update(){
        stud.updateOne(eq("i",1),set("i",13333));
    }

    /**
     * 单例 mongoclient
     */
    @Test
    public void testSingole(){
        MongoClient client=MongodbUtil.getClient("localhost","root","root");
        MongoClient client1=MongodbUtil.getClient("localhost","root","root");
        Assert.assertEquals(client,client1);
    }
}

