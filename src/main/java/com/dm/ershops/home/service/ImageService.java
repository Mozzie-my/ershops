package com.dm.ershops.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.Image;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public interface ImageService extends IService<Image> {

    //获取商品对应图片的map
    public Map<String,String> getBigImage (List<Goods> goodsList);
}
