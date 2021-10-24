package com.dm.ershops.home.Controler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class UserControler {


    @GetMapping("/login")
    public String goLogin(){
        return "login";
    }


    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String LoginEnsure(@RequestParam String name, @RequestParam String pass, Model model, HttpSession session){
        User user=new User();
        user = userService.getOne(new QueryWrapper<User>().eq("username", name),false);
        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        log.info(pass);
        System.out.println(user);
        if(user!=null && user.getUsername().equals(name) && user.getPassword().equals(pass)){
            session.setAttribute("User",user);
            return "redirect:/index";
        }else{
            session.setAttribute("msg", "账号不存在或账号密码输入错误");
            return "redirect:/login";
        }
    }
    @PostMapping("userUpdate")
    public String userUpdate(@RequestParam() String oldPass,
                             @RequestParam() String pass,
                             @RequestParam() String enPass,
                             HttpSession session,
                             Model model){
        if (oldPass.equals(pass)){
            model.addAttribute("msg","更新失败，新密码与原密码相同");
            model.addAttribute("url","userupdate.html");
            return "userback/tips";
        }
        if (!pass.equals(enPass)){
            model.addAttribute("msg","更新失败，密码不一致");
            model.addAttribute("url","userupdate.html");
            return "userback/tips";
        }else{
            User user=(User)session.getAttribute("User");
            user.setPassword(DigestUtils.md5DigestAsHex(pass.getBytes()));
            userService.updateById(user);
            model.addAttribute("msg","更新成功！！");
            model.addAttribute("url","userupdate.html");
            return "userback/tips";
        }
    }


    @PostMapping("register")
    public String register(@RequestParam() String name,
                           @RequestParam() String qq,
                           @RequestParam() String phone,
                           @RequestParam() String pass,
                           @RequestParam() String enPass){
        if (pass.equals(enPass)){
            User user=new User();
            user.setUsername(name);
            user.setPhone(phone);
            user.setPassword(DigestUtils.md5DigestAsHex(pass.getBytes()));
            user.setQq(qq);
            userService.save(user);
            return "/login";
        }{
            return "/register";
        }

    }
    @GetMapping("register")
    public String goRegister(){
        return "register";
    }


}
