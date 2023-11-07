package com.zbbmeta.controller;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */

import com.zbbmeta.dto.EmployeeLoginDTO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {



    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public EmployeeLoginDTO login(@RequestBody EmployeeLoginDTO employeeLoginDTO) 	{

        return employeeLoginDTO;
    }

    /**
     * 状态
     *
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("员工状态")
    public String status() {
        return "status 为登录";
    }

    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public String logout() {
        return "退出成功";
    }

}