package com.dm.ershops.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.ershops.home.bean.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService extends IService<Goods> {


    public List<Goods> getByUserId(int id);

}
