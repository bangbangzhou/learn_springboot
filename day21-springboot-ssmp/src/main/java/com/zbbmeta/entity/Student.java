package com.zbbmeta.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author springboot葵花宝典
 * @since 2023-08-20
 */
@TableName("tb_student")
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
//    @TableId(value = "id", type = IdType.ASSIGN_ID    )
    private String id;

    /**
     * 学号
     */
    private String stuid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0 男 1 女
     */
    private Integer sex;

    /**
     * 院系
     */
    private String dept;

    /**
     * 家庭地址
     */
    private String address;
}
