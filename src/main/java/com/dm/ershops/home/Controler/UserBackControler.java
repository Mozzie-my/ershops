package com.dm.ershops.home.Controler;


import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserBackControler {



    @GetMapping("/userback")
    public String UserBack(HttpSession session,Model model) {
        if (session.getAttribute("User")==null){
            return "login";
        }
        return "userback/index";
    }

    @GetMapping("/goodsadd.html")
    public String goodsAdd(){
        return "userback/goodsadd";
    }


    @Autowired
    GoodsService goodsService;
    @GetMapping("/goodstable.html")
    public String goodsTable(Model model,HttpSession session){
        User user=(User) session.getAttribute("User");
        model.addAttribute("MyGoodsList",goodsService.getByUserId(user.getId()));
        return "userback/goodstable";
    }

    @GetMapping("/goodsupdate.html")
    public String goodsupdate(int id,Model model){
        model.addAttribute("Goods",goodsService.getById(id));
        return "userback/goodsupdate";
    }

    @GetMapping("/userupdate.html")
    public String userupdate(){
        return "userback/userupdate";
    }
}
