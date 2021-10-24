package com.dm.ershops.home.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @TableId(type = IdType.AUTO)
    private int id;
    private int goodsId;
    private String imgUrl;
}
