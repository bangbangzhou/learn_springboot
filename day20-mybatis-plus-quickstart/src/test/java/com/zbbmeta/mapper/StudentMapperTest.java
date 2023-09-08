package com.zbbmeta.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testGetAll(){
        List<Student> studentList = studentMapper.selectList(null);
        System.out.println("studentList = " + studentList);
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
        studentMapper.insert(student);
    }
    /**
     * 根据ID删除
     */
    @Test
    void testDelete() {
        studentMapper.deleteById(1);
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
        studentMapper.updateById(student);
    }


    //分页查询
    @Test
    void testSelectPage(){
        //1 创建IPage分页对象,设置分页参数
        IPage<Student> page=new Page<>(1,3);
        //2 执行分页查询
        studentMapper.selectPage(page,null);
        //3 获取分页结果
        System.out.println("当前页码值："+page.getCurrent());
        System.out.println("每页显示数："+page.getSize());
        System.out.println("总页数："+page.getPages());
        System.out.println("总条数："+page.getTotal());
        System.out.println("当前页数据："+page.getRecords());
    }

    //分页查询
    @Test
    void testCondition(){
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.select("username as name","count(*) as nums");
        qw.groupBy("name");
        List<Map<String, Object>> maps = studentMapper.selectMaps(qw);
        System.out.println(maps);
    }

    /**
     * 根据ID查询
     */
    @Test
    void testGetById() {
        Student student = studentMapper.selectById(2L);
        System.out.println(student);
    }


    @Test
    public  void testdeleteBatchIds(){
        List<Long> list = new ArrayList<>();
        list.add(6L);
        list.add(7L);
        studentMapper.deleteBatchIds(list);
    }

    @Test
    public  void testselectBatchIds(){
        List<Long> list = new ArrayList<>();
        list.add(6L);
        list.add(7L);
        studentMapper.selectBatchIds(list);
    }

    @Test
    void testLogicDeleted() {
        int row = studentMapper.deleteById(5);
        System.out.println(row + "条记录被逻辑删除");
    }
}
