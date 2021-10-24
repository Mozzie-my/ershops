package com.dm.ershops.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.ershops.home.bean.Carts;
import com.dm.ershops.home.mapper.CartsMpaaer;
import com.dm.ershops.home.service.CartsService;
import org.springframework.stereotype.Service;

@Service
public class CartsServiceImpl extends ServiceImpl<CartsMpaaer, Carts> implements CartsService {

}
