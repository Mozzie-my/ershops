package com.dm.ershops.home.Controler;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.ershops.home.bean.Carts;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.CartsService;
import com.dm.ershops.home.service.GoodsService;
import com.dm.ershops.home.service.ImageService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartsControler {


    @Autowired
    CartsService cartsService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ImageService imageService;
    @GetMapping("/carts")
    public String goCarts(HttpSession session, Model model){
        User user= (User) session.getAttribute("User");
        //if User==null则跳转到登陆页面
        if (user != null){
            List<Carts> list=cartsService.list(new QueryWrapper<Carts>().eq("userid",user.getId()));
            List<Goods> list1= new ArrayList<>();
            for (Carts i: list) {
                list1.add(goodsService.getById(i.getGoodsid()));
            }
            Map<String,String> imageList=imageService.getBigImage(list1);
            model.addAttribute("cartslist",list1);
            model.addAttribute("imagelist",imageList);
            return "carts";
        }
        model.addAttribute("Msg","您还没有登录请登录！");
        return "login";
    }

    @GetMapping("/car")
    public String gocart(){
        return "carts";
    }

    @GetMapping("/CartsDeleteOne")
    public String deleteOne(@RequestParam() int userid,@RequestParam() int goodsid){
        Boolean is = cartsService.remove(new QueryWrapper<Carts>().eq("userid",userid).eq("goodsid",goodsid));
        System.out.println(is);
        return "redirect:/carts";
    }

    /**
     * 添加购物车
     * @param goodsid
     */
    @GetMapping("/addCarts")
    public String saveCarts(@RequestParam() int goodsid,HttpSession session,Model model){
        User user = (User) session.getAttribute("User");
        if (user!=null){
            Carts carts=new Carts();
            carts.setGoodsid(goodsid);
            carts.setUserid(user.getId());
            cartsService.save(carts);
            return "redirect:/goods?id="+String.valueOf(goodsid)+"&Msg=1";
        }else {
            return "redirect:/goods?id="+String.valueOf(goodsid)+"&Msg=0";
        }
    }
}
