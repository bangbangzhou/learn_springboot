package com.zbbmeta;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private IStudentService studentService;

    @Test
    void testGetById(){
        System.out.println(studentService.getById("1692936528352854018"));
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
        studentService.save(student);
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
        studentService.updateById(student);
    }
    @Test
    void testDelete(){
        studentService.removeById(18);
    }

    @Test
    void testGetAll(){
        studentService.list();
    }

    @Test
    void testGetPage(){

        IPage<Student> page = studentService.getPage(2, 5, new Student());
        System.out.println(page.getRecords());
    }
}
