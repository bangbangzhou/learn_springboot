package com.zbbmeta.elastic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
@NoArgsConstructor
public class WineShopDoc {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String location;
    private String pic;

    public WineShopDoc(WineShop wineShop) {
        this.id = wineShop.getId();
        this.name = wineShop.getName();
        this.address = wineShop.getAddress();
        this.price = wineShop.getPrice();
        this.score = wineShop.getScore();
        this.brand = wineShop.getBrand();
        this.city = wineShop.getCity();
        this.starName = wineShop.getStarName();
        this.business = wineShop.getBusiness();
        this.location = wineShop.getLatitude() + ", " + wineShop.getLongitude();
        this.pic = wineShop.getPic();
    }
}
