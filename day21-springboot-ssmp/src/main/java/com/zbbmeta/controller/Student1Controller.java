package com.zbbmeta.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springboot葵花宝典
 */
@RestController
@RequestMapping("/students1")
public class Student1Controller {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public List<Student> getAll(){
        return   studentService.list();
    }

    @PostMapping
    public Boolean save(@RequestBody Student student) throws IOException {
        return  studentService.save(student);
    }

    @PutMapping
    public Boolean update(@RequestBody Student student) throws IOException {
        return  studentService.updateById(student);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable String id){
        return studentService.removeById(id);
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable String  id){
        return studentService.getById(id);
    }

    @PostMapping("{currentPage}/{pageSize}")
    public   IPage<Student> getPage(@PathVariable int currentPage, @PathVariable int pageSize, @RequestBody Student student){

       return studentService.getPage(currentPage, pageSize, student);
    }
}
