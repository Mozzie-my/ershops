package com.dm.ershops.home.Controler;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.ershops.home.bean.Admin;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.AdminService;
import com.dm.ershops.home.service.GoodsService;
import com.dm.ershops.home.service.UserService;
import com.mysql.cj.Session;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class AdminControler {

    @GetMapping(value = {"/admin","/admin/login"})
    public String loginPage(){
        return "admin/login";
    }


    @Autowired
    AdminService adminService;
    @PostMapping("admin/login")
    public String loginJudge(@RequestParam String name, @RequestParam String pass, Model model,HttpSession session){
        Admin admin = new Admin();
//            根据name查询记录是否存在
        admin = adminService.getOne(new QueryWrapper<Admin>().eq("name",name));
        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        log.info(pass);
        System.out.println(admin);
        if(admin!=null && admin.getName().equals(name) && admin.getPassword().equals(pass)){
            session.setAttribute("loginAdmin",admin);
            return "redirect:/index.html";
        }else{
            session.setAttribute("msg", "账号不存在或账号密码输入错误");
            model.addAttribute("msg", "账号不存在或账号密码输入错误");
            return "redirect:/admin";
        }
    }

    /**
     * 去首页
     * @return
     */
    @GetMapping("/index.html")
    public String MainPage(HttpSession session,Model model){
        Object loginAdmin = session.getAttribute("loginAdmin");
        if (loginAdmin!=null){
            return "/admin/index";
        }else{
            model.addAttribute("msg","非法访问");
            return "/admin/login";
        }
    }

    /**
     * 订单管理
     * @return
     */
    @GetMapping("admin/role.html")
    public String role(){
        return "admin/role";
    }
    /**
     * 用户管理
     * @return
     */


    @Autowired
    UserService userService;
    @GetMapping("admin/user.html")
    public String user(@RequestParam(value = "pg",defaultValue = "1") Integer pg,Model model){
//        List <User> list= userService.list();
//        model.addAttribute("UserList",list);
        Page<User> userPage = new Page<>(pg,10);
        Page<User> page = userService.page(userPage,null);
        model.addAttribute("page",page);
        return "admin/user";
    }



    @Autowired
    GoodsService goodsService;
    /**
     * 商品管理
     * @return
     */
    @GetMapping("admin/newsType.html")
    public String newType(@RequestParam(value = "pg",defaultValue = "1") Integer pg,Model model){
        Page<Goods> userPage = new Page<>(pg,10);
        Page<Goods> page = goodsService.page(userPage,null);

        model.addAttribute("page",page);
        return "admin/newsType";
    }
    /**
     * 留言管理
     * @return
     */
    @GetMapping("admin/news.html")
    public String news(){
        return "admin/news";
    }
    /**
     * 更新密码
     * @return
     */
    @GetMapping("admin/updatePwd.html")
    public String updatePwd(){
        return "admin/updatePwd";
    }
}
