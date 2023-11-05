package com.zbbmeta.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbbmeta.entity.Student;
import com.zbbmeta.mapper.StudentMapper;
import com.zbbmeta.service.IStudentService;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.Counter;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springboot葵花宝典
 * @since 2023-08-20
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {



    /**
     * 条件+分页查询
     * @param currentPage
     * @param pageSize
     * @param student
     * @return
     */
   @Override
    public IPage<Student> getPage(int currentPage, int pageSize, Student student){
        //1 创建IPage分页对象,设置分页参数
        IPage<Student> page=new Page<>(currentPage,pageSize);
        //2 执行分页查询
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
       if(student!=null){
           lqw.eq(StrUtil.isNotEmpty(student.getStuid()),Student::getStuid,student.getStuid());
           lqw.like(StrUtil.isNotEmpty(student.getName()),Student::getName,student.getName());
           lqw.like(StrUtil.isNotEmpty(student.getDept()),Student::getDept,student.getDept());
           lqw.like(StrUtil.isNotEmpty(student.getAddress()),Student::getAddress,student.getAddress());
       }
       IPage<Student> studentList = this.page(page, lqw);
       //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        return studentList;
    }
}
