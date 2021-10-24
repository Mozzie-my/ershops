package com.dm.ershops.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.Image;
import com.dm.ershops.home.mapper.ImageMapper;
import com.dm.ershops.home.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    public Map<String,String> getBigImage (List<Goods> goodsList){
        Map<String,String> map=new HashMap<String,String>();
        for (Goods i: goodsList) {
            map.put(String.valueOf(i.getId()),this.getById(i.getId()).getImgUrl());
        };
        return map;
    }
}
