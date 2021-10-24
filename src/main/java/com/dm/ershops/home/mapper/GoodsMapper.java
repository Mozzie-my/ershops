package com.dm.ershops.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.ershops.home.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
