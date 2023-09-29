package com.zbbmeta.seatastudy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("tb_account")
public class Account {
    @TableId
    private Long id;
    private String userId;
    private Integer money;
}
