package com.dm.ershops.home.Controler;


import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.GoodsService;
import com.dm.ershops.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PayControler {

    @GetMapping("/pay")
    public String goPay(){
        return "/pay";
    }


    @Autowired
    GoodsService goodsService;


    @Autowired
    UserService userService;

    /**
     * 支付以后点转到支付成功界面
     * @param id
     * @return
     */
    @GetMapping("/payone")
    public String payone(@RequestParam() int id, Model model){
        Goods goods = goodsService.getById(id);
        goods.setEndTime(LocalDateTime.now().toString());
        goodsService.updateById(goods);
        User user=userService.getById(goods.getUserId());
        model.addAttribute("sellUser", user);
        return "pay";
    }
}
