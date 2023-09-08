package com.zbbmeta;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Day21SpringbootSsmpApplicationTests {

    @Autowired
    private IStudentService studentService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetPage(){

        IPage<Student> page = studentService.getPage(1, 10, null);
        System.out.println(page.getRecords());
    }

    @Test
    public void testInsert(){
        Student student = new Student();
        student.setStuid("6840300");
        student.setName("张三");
        student.setAge(20);
        student.setSex(0);
        student.setDept("计算机学院");
        student.setAddress("广东省广州市天河街100号");

        Student student1 = new Student();
        student1.setStuid("6840000");
        student1.setName("李四");
        student1.setAge(24);
        student1.setSex(0);
        student1.setDept("计算机学院");
        student1.setAddress("广东省珠海市香洲路100号");


        Student student2 = new Student();
        student2.setStuid("6840500");
        student2.setName("王五");
        student2.setAge(25);
        student2.setSex(0);
        student2.setDept("物理学院");
        student2.setAddress("广东省广州市番禺100号");


        Student student3 = new Student();
        student3.setStuid("6840600");
        student3.setName("邹六");
        student3.setAge(26);
        student3.setSex(0);
        student3.setDept("艺术学院");
        student3.setAddress("河南省郑州市100号");


        Student student4 = new Student();
        student4.setStuid("6840700");
        student4.setName("韩梅梅");
        student4.setAge(21);
        student4.setSex(1);
        student4.setDept("英语学院");
        student4.setAddress("广东省广州市天河街150号");


        Student student5 = new Student();
        student5.setStuid("6840800");
        student5.setName("小芳");
        student5.setAge(20);
        student5.setSex(1);
        student5.setDept("英语学院");
        student5.setAddress("河南省郑州市140号");


        Student student6 = new Student();
        student6.setStuid("6840900");
        student6.setName("小花");
        student6.setAge(20);
        student6.setSex(1);
        student6.setDept("计算机学院");
        student6.setAddress("广东省深圳市100号");


        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);


        studentService.saveBatch(students);
    }

}
