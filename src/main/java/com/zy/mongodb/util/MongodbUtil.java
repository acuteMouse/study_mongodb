package com.zy.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @autor cgl
 * @time 2016/9/28.
 * 作用:
 */
public class MongodbUtil {
    static MongoClient client = null;
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 27017;

    /**
     * 连接本地数据库
     *
     * @return
     */
    public static MongoClient getDefaultClient() {
        return getClient(DEFAULT_HOST, null, null, null);
    }

    /**
     * 连接指定host
     *
     * @param host
     * @return
     */
    public static MongoClient getClientWithHost(String host) {
        return getClient(host, null, null, null);
    }

    /**
     * 单例mongodbclient,双锁,连接默认端口27017,最底层
     *
     * @return
     */
    public static MongoClient getClient(String host, Long port, String userName, String password) {
        if (client == null) {
            synchronized (MongoClient.class) {
                if (client == null) return client = new MongoClient(host, DEFAULT_PORT);
            }
        }
        return client;
    }

    /**
     * 使用用户名、密码连接指定的database
     *
     * @param host
     * @param database
     * @param name
     * @param password
     * @return
     */

    public static MongoDatabase getMongodbDataBase(String host, String database, String name, String password) {
        MongoDatabase mongoDatabase = getClient(host, null, null, null).getDatabase(database);
        return mongoDatabase;

    }

    /**
     * 默认连接，不实用用户名和密码
     *
     * @param host     主机ip
     * @param database 数据库名
     * @return
     */
    public static MongoDatabase getMongodbDataBase(String host, String database) {
        return getClientWithHost(host).getDatabase(database);
    }

    /**
     * 获取本地mongodb数据库
     *
     * @param database 数据库名
     * @return
     */
    public static MongoDatabase getDefaultMongodbDataBase(String database) {
        return getDefaultClient().getDatabase(database);
    }

    public void closeConnection() {
        client.close();
    }
}
