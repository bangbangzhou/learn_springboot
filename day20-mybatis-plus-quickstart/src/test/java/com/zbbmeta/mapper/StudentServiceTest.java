package com.zbbmeta.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 周棒棒
 * @ClassName springboot葵花宝典
 * @description: TODO
 * @date 2023年08月17日
 * @version: 1.0
 */
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    /**
     * 根据ID查询
     */
    @Test
    void testGetById() {
        Student student = studentService.getById(2L);
        System.out.println(student);
    }

    /**
     * 新增
     */
    @Test
    void testSave() {
        Student student = new Student();
        student.setName("周久良3");
        student.setAge(99);
        student.setEmail("zhoujiuliang3@zbbmeta.cn");
        studentService.save(student);
    }
    /**
     * 根据ID删除
     */
    @Test
    void testDelete() {
        studentService.removeById(1);
    }
    /**
     * 更新数据
     */
    @Test
    void testUpdate() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三三");
        student.setAge(99);
        studentService.updateById(student);
    }
    //分页查询
    @Test
    void testSelectPage(){
        //1 创建IPage分页对象,设置分页参数
        IPage<Student> page=new Page<>(1,3);
        //2 执行分页查询
        studentService.page(page,null);
        //3 获取分页结果
        System.out.println("当前页码值："+page.getCurrent());
        System.out.println("每页显示数："+page.getSize());
        System.out.println("总页数："+page.getPages());
        System.out.println("总条数："+page.getTotal());
        System.out.println("当前页数据："+page.getRecords());
    }
}
