package com.dm.ershops.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.mapper.GoodsMapper;
import com.dm.ershops.home.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    public List<Goods> getByUserId(int id){
        return this.list(new QueryWrapper<Goods>().eq("user_id",id));
    }
}
