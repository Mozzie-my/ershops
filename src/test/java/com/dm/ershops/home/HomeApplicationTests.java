package com.dm.ershops.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Slf4j
class HomeApplicationTests {



    @Autowired
    UserService userService;

    @Test
    void  contextLoads() {


        Gson gson=new Gson();
        String jsonResult=gson.toJson(userService.list());
        System.out.println(jsonResult);



}

}
