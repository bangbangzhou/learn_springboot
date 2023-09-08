package com.zbbmeta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbbmeta.clients.entity.R;
import com.zbbmeta.clients.entity.Student;
import com.zbbmeta.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springboot葵花宝典
 * @since 2023-08-20
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public R getAll(){
        return new R(true, studentService.list());
    }

    @PostMapping
    public R save(@RequestBody Student student) throws IOException {
        boolean flag = studentService.save(student);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    @PutMapping
    public R update(@RequestBody Student student) throws IOException {
        boolean flag = studentService.updateById(student);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        return new R(studentService.removeById(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String  id){
        return new R(true, studentService.getById(id));
    }

    @PostMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, @RequestBody Student student){

        IPage<Student> page = studentService.getPage(currentPage, pageSize, student);
        if( currentPage > page.getPages()){
            page = studentService.getPage((int)page.getPages(), pageSize,null);
        }
        return new R(true, page);
    }

/*    @PostMapping("/{currentPage}/{pageSize}")
    public R getAll(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        IPage<Student> page = studentService.getPage(currentPage, pageSize,null);
        if( currentPage > page.getPages()){
            page = studentService.getPage((int)page.getPages(), pageSize,null);
        }
        return new R(null != page ,page);
    }*/
}
