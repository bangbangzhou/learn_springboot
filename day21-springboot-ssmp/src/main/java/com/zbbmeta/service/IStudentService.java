package com.zbbmeta.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.R;
import com.zbbmeta.clients.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springboot葵花宝典
 * @since 2023-08-20
 */
public interface IStudentService extends IService<Student> {
    public IPage<Student> getPage( int currentPage,  int pageSize, Student student);
}
