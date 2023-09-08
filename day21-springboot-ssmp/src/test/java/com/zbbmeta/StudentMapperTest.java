package com.zbbmeta;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.List;


/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void testGetById(){
        System.out.println(studentMapper.selectById(1));
    }

    @Test
    void testSave(){

        Student student = new Student();
        student.setStuid("6843000");
        student.setName("张绍港");
        student.setAge(20);
        student.setSex(0);
        student.setDept("新闻学院");
        student.setAddress("广东省广州市天河街100号");
        studentMapper.insert(student);
    }

    @Test
    void testUpdate(){
        Student student = new Student();
        student.setId("1692936528352854022");

        student.setName("小花2");
        student.setAge(25);
        student.setSex(1);
        student.setDept("新闻学院");
        student.setAddress("广东省广州市天河街100号");
        studentMapper.updateById(student);
    }

    @Test
    void testDelete(){
        studentMapper.deleteById("1692936528352854022");
    }

    @Test
    void testGetAll(){
        studentMapper.selectList(null);
    }

    @Test
    void testGetPage(){
        IPage page = new Page(2,5);
        studentMapper.selectPage(page, null);
        System.out.println("当前页：===》"+page.getCurrent());
        System.out.println("每页个数：===》"+page.getSize());
        System.out.println("数据总数：===》"+page.getTotal());
        System.out.println("总页数：===》"+page.getPages());
        System.out.println("数据：===》"+page.getRecords());
    }

    @Test
    void testGetBy(){
        String name ="张三";
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.like(name!=null,Student::getName,name);
        List<Student> studentList = studentMapper.selectList(lqw);
        System.out.println("studentList = " + studentList);
    }
}
