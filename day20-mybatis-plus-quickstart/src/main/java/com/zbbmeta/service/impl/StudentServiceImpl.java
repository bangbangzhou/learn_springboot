package com.zbbmeta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbbmeta.entity.Student;
import com.zbbmeta.mapper.StudentMapper;
import com.zbbmeta.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author springboot葵花宝典
 * @ClassName StudentServiceImpl
 * @description: TODO
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
