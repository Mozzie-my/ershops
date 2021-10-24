package com.dm.ershops.home.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int id;
    private int catelogId;//商品分类，外键
    private int userId;
    private String name;
    private float price;//出售价格
    private float realPrice;//真实价格
    private String startTime;
    private String polishTime;
    private String endTime;
    private String describle;//详细信息
    private int commetNum;//评论回复数量
}
