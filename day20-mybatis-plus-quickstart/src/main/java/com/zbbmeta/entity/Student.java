package com.zbbmeta.clients.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
//@TableName("tb_student")
public class Student {

    @TableId(value = "ID",type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "username",select = false)
    private String name;
    private Integer age;
    private String email;
    @TableField(exist = false)
    private String address;
    //逻辑删除字段，标记当前记录是否被删除
//    @TableLogic(value = "0",delval = "1")
    private Integer status;

    /**
     * 实现乐观锁
     */
    @Version
    private Integer version;
}
