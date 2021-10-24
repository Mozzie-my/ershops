package com.dm.ershops.home.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.mapper.UserMapper;
import com.dm.ershops.home.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
