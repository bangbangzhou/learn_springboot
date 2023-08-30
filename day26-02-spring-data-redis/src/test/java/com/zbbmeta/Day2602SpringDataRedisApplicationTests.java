package com.zbbmeta;

import com.zbbmeta.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class Day2602SpringDataRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){

        //目标：获取字符串操作类对字符串数据实现redis数据库存储操作
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //1.写入字符串数据 set key value
        valueOperations.set("name1","admin");
        String name1 = (String) valueOperations.get("name1");
        System.out.println("name1="+name1);
        //2.写入字符串数据 setex key seconds value
        valueOperations.set("name2","admin2",1, TimeUnit.MINUTES); //设置过期1分钟
        //3.写入字符串数据 setnx key seconds value
        valueOperations.setIfAbsent("name3","admin3");
        //4.删除字符串数据 del value
        redisTemplate.delete("name3");

        //5.存储java对象
        User user = new User();
        user.setAge(20);
        user.setName("播仔");
        valueOperations.set("bozai",user);
        User user2 = (User) valueOperations.get("bozai");

    }


    /**
     * 操作hash类型
     */
    @Test
    public void testHash(){
        //1. 得到操作hash类型的客户端对象
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        //存储
        hashOperations.put("person","id","110");
        hashOperations.put("person","name","狗娃");
        //取
        System.out.println("姓名："+ hashOperations.get("person","name"));
        //所有field
        Set<Object> set = hashOperations.keys("person");
        for (Object key : set) {
            System.out.print(key+",");
        }
        System.out.println();

        //所有值
        List<Object> list = hashOperations.values("person");
        for (Object value : list) {
            System.out.print(value+",");
        }
        System.out.println();
    }


    /**
     * 操作list类型
     */
    @Test
    public void testList(){
        //1. 得到操作list对象的客户端对象
        ListOperations<Object, Object> listOperations = redisTemplate.opsForList();
        //2. 存
        listOperations.leftPushAll("list","张三","李四","王五");

        //3. 取
        System.out.println("list集合的元素："+listOperations.range("list",0,-1));

        //4.得到长度
        System.out.println("长度："+ listOperations.size("list"));
    }

    @Test
    public void testSet(){
        //1. 得到操作set对象的客户端对象
        SetOperations<Object, Object> setOperations = redisTemplate.opsForSet();
        //存
        setOperations.add("set","洋洋","张庭","凡凡","签签","洋洋");
        //取
        Set<Object> set = setOperations.members("set");
        set.forEach(System.out::println);

        //删
        setOperations.remove("set","洋洋");

        //查看内容
        System.out.println("=========删除后的内容=========");
        set = setOperations.members("set");
        set.forEach(System.out::println);
    }
    /**
     * 操作zset类型
     */
    @Test
    public void testzSet(){
        //1. 得到操作zset对象的客户端对象
        ZSetOperations<Object, Object> zSetOperations = redisTemplate.opsForZSet();

        //2. 存
        zSetOperations.add("zset1","洋洋",100);
        zSetOperations.add("zset1","林荣",180);
        zSetOperations.add("zset1","尚晋",250);

        //取
        Set<Object> zset1 = zSetOperations.range("zset1", 0, -1);
        //注意：降序查询 zSetOperations.reverseRange("zset1",0,-1);
        for (Object name : zset1) {
            System.out.println("姓名："+ name+" 分数："+zSetOperations.score("zset1",name));
        }

        //修改
        zSetOperations.incrementScore("zset1","尚晋",-2);


        //取
        System.out.println("=======修改后的分数==========");
        zset1 = zSetOperations.range("zset1", 0, -1);
        for (Object name : zset1) {
            System.out.println("姓名："+ name+" 分数："+zSetOperations.score("zset1",name));
        }
    }
    /**
     * 通用操作
     */
    @Test
    public void baseTest(){
        //1. 所有键
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("=======所有键==========");
        for (String key : keys) {
            System.out.print(key+",");
        }
        System.out.println();
        //2. 判断有没有键
        Boolean flag = redisTemplate.hasKey("set");
        System.out.println("是否存在set1："+ flag);
        //3. 类型
        System.out.println("类型："+redisTemplate.type("set"));

        //4. 删除
        redisTemplate.delete("set");
    }
}
