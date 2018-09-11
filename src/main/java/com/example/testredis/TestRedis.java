package com.example.testredis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRedis {

    /**
     * 测试连接
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());
    }

    /**
     * 测试插入字符串
     */
    @Test
    public void test2() {
        Jedis jedis = new Jedis("localhost");
        jedis.set("testName", "zhangsan");
        System.out.println(jedis.get("testName"));
    }

    /**
     * 测试插入集合
     */
    @Test
    public void test3() {
        Jedis jedis = new Jedis("localhost");
        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0, 5);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Stored string in redis:: " + list.get(i));
        }
    }

    /**
     * 测试查询键
     */
    @Test
    public void test4() {
        Jedis jedis = new Jedis("localhost");
        Set<String> list = jedis.keys("*");
        for (String key : list) {
            System.out.println(key);
        }
    }

    /**
     * 测试存储map
     */
    @Test
    public void test5() {
        Jedis jedis = new Jedis("localhost");
        Map<String,String> user = new HashMap<String,String>();
        user.put("name", "cd");
        user.put("password", "123456");
        //map存入redis
        jedis.hmset("user", user);
    }

}
