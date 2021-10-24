package com.dm.ershops.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.ershops.home.bean.Admin;
import com.dm.ershops.home.mapper.AdminMapper;
import com.dm.ershops.home.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
