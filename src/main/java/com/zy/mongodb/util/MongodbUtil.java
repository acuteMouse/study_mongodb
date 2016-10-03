package com.zy.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @autor cgl
 * @time 2016/9/28.
 * 作用:
 */
public class MongodbUtil {
     static MongoClient client=null;

    /**
     * 单例mongodbclient
     *
     * @return
     */
    public  static  MongoClient getClient(String host,String password,String user) {
        if (client == null) {
            synchronized (MongoClient.class) {
                return client = new MongoClient(host,27017);
            }
        }
            return client;
    }
    /**
     * 使用用户名、密码连接指定的database
     * @param host
     * @param database
     * @param name
     * @param password
     * @return
     */

    public static  MongoDatabase getMongodbDataBase(String host,String database,String name,String password){
        MongoDatabase mongoDatabase =getClient(host,name,password).getDatabase(database);
        return mongoDatabase;

    }

    /**
     * 默认连接，不实用用户名和密码
     * @param host 主机ip
     * @param database 数据库名
     * @return
     */
    public static  MongoDatabase getMongodbDataBase(String host,String database){
        client=new MongoClient(host,27017);
        MongoDatabase mongoDatabase =client.getDatabase(database);
        return mongoDatabase;
    }

    /**
     * 获取本地mongodb数据库
     * @param database 数据库名
     * @return
     */
    public static  MongoDatabase getDefaultMongodbDataBase(String database){
        client=new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase =client.getDatabase(database);
        return mongoDatabase;
    }
    public void closeConnection(){
        client.close();
    }
}
