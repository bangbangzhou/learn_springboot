package com.zbbmeta.elastic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * @TableName tb_wine_shop
 */
@TableName(value ="tb_wine_shop")
@Data
@ToString
@EqualsAndHashCode
public class WineShop implements Serializable {
    /**
     * 酒店id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 酒店地址
     */
    private String address;

    /**
     * 酒店价格
     */
    private Integer price;

    /**
     * 酒店评分
     */
    private Integer score;

    /**
     * 酒店品牌
     */
    private String brand;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    private String starName;

    /**
     * 商圈
     */
    private String business;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 酒店图片
     */
    private String pic;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}