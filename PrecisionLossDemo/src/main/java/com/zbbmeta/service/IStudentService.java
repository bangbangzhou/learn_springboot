package com.zbbmeta.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbbmeta.entity.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springboot葵花宝典
 * @since 2023-08-20
 */
public interface IStudentService extends IService<Student> {
    public IPage<Student> getPage(int currentPage, int pageSize, Student student);
}
