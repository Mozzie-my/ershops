package com.dm.ershops.home.Controler;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.Image;
import com.dm.ershops.home.service.GoodsService;
import com.dm.ershops.home.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexControler {


    @Autowired
    GoodsService goodsService;

    @Autowired
    ImageService imageService;
    @GetMapping(value = {"/","/index"})
    public String index(Model model, HttpSession session){
        Page<Goods> goodspage = new Page<>(1,20);
        Page<Goods> page = goodsService.page(goodspage,null);
        Map<String,String> imageList=imageService.getBigImage(page.getRecords());
        System.out.println(imageList.get("3"));
        model.addAttribute("imagelist",imageList);
        model.addAttribute("goodsPage",page);
        return "index";
    }

}
