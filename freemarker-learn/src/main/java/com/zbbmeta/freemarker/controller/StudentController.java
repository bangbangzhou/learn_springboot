package com.zbbmeta.freemarker.controller;

import com.zbbmeta.freemarker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Controller //用于跳转页面，而非返回json数据
@RequestMapping("/student")
public class StudentController {
    /**
     * 入门
     */
    @GetMapping
    public String hello(Model model){

        //存储数据
        model.addAttribute("name","jack");

        Student student = new Student();
        student.setName("如花");
        model.addAttribute("student",student);

        //路径： prefix+student+suffix
        //classpath:/templates/student.ftl
        return "student";//返回展示数据的页面名称
    }

    @GetMapping("/list")
    public String list(Model model) {

        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());

        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //向model中存放List集合数据
        model.addAttribute("stus",stus);


        return "list";
    }


    @GetMapping("/map")
    public String map(Model model) {

        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());

        //创建Map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        // 3.1 向model中存放Map数据
        model.addAttribute("stuMap", stuMap);

        return "map";
    }
}
