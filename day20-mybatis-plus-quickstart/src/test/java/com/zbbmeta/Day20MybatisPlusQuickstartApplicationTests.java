package com.zbbmeta;

import com.zbbmeta.entity.Student;
import com.zbbmeta.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day20MybatisPlusQuickstartApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void testUpdateOne() {
        //1.先通过要修改的数据id将当前数据查询出来
        Student student = studentMapper.selectById(2L);
        //2.将要修改的属性逐一设置进去
        student.setName("李四四");
        studentMapper.updateById(student);
    }

    @Test
    public void testUpdateTwo() {
        //先通过要修改的数据id将当前数据查询出来
        Student student1 = studentMapper.selectById(2L);     //version=2
        Student student2 = studentMapper.selectById(2L);    //version=2
        student1.setName("李四四2");
        studentMapper.updateById(student1);              //version=>3
        student2.setName("李四四2");
        studentMapper.updateById(student2);               //verion=2 更新失败
    }
}
