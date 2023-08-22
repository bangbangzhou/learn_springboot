package com.zbbmeta.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author springboot葵花宝典
 * @description: 日志对象
 */
@Data
@Accessors(chain = true)
public class CommonLog   implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志类型
     */
    private String type;
    /**
     * 跟踪ID
     */
    private String traceId;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 操作内容
     */
    private String operation;
    /**
     * 执行方法
     */

    private String method;

    /**
     * 请求路径
     */
    private String url;
    /**
     * 参数
     */
    private String params;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 耗时
     */
    private Long executeTime;
    /**
     * 地区
     */
    private String location;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标识
     */
    private String isDeleted;
    /**
     * 租户ID
     */

    private Integer tenantId;
    /**
     * 异常信息
     */

    private String exception;
}
