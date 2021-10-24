package com.dm.ershops.home.Controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.ershops.home.bean.Goods;
import com.dm.ershops.home.bean.Image;
import com.dm.ershops.home.bean.User;
import com.dm.ershops.home.service.GoodsService;
import com.dm.ershops.home.service.ImageService;
import com.dm.ershops.home.service.UserService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class GoodsController {



    @Autowired
    GoodsService goodsService;


    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;
    @GetMapping("/goods")
    public String goodsShow(@RequestParam Integer id,String Msg, Model model) {
        Goods goods = goodsService.getById(id);
        List<Image> list = imageService.list(new QueryWrapper<Image>().eq("goods_id",id));
        User user = userService.getById(goods.getUserId());
        model.addAttribute("goods",goods);
        model.addAttribute("sellUser",user);
        model.addAttribute("imageList",list);
        return "/list_details";
    }


    @PostMapping("/addgoods")
    public String addGoods(@RequestParam("name") String name,
                           @RequestParam("price") String price,
                           @RequestParam("describe") String describe,
                           @RequestPart("photos") MultipartFile [] photos,
                           HttpSession session,
                           Model model) throws IOException {
        Goods goods=new Goods();
        User user= (User) session.getAttribute("User");
        goods.setName(name);
        goods.setUserId(user.getId());
        goods.setDescrible(describe);
        goods.setRealPrice(Float.valueOf(price));
        goods.setStartTime(LocalDateTime.now().toString());
        goodsService.save(goods);
        //获取用户id
        int id=goodsService.getOne(new QueryWrapper<Goods>().eq("name",name)).getId();
        if (photos.length>0){
           for (MultipartFile photo : photos) {
               if (!photo.isEmpty()){
                   //获取项目路径
                   String basePath = ClassUtils.getDefaultClassLoader().getResource("static/img/").getPath();
                   //生成图片名
                   String photoName = UUID.randomUUID().toString()+".jpg";
                   Image image=new Image();
                   image.setGoodsId(id);
                   image.setImgUrl("img/"+photoName);
                   imageService.save(image);
                   photo.transferTo(new File(basePath+photoName));
               }
           }
       }
        model.addAttribute("msg","添加成功！！");
        model.addAttribute("url","/goodsadd.html");
       return "userback/tips";
    }

    /**
     * 删除goods方法
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/deleteGoods")
    public String deleteGoods(@RequestParam() int id,Model model){
        model.addAttribute("url","goodstable.html");
        if (goodsService.removeById(id)){
            model.addAttribute("msg","删除成功！");
            return "userback/tips";
        }
        model.addAttribute("msg","删除失败！");
        return "userback/tips";
    }


    @PostMapping("/goodsupdate")
    public String goodsUpadte(@RequestParam("name") String name,
                              @RequestParam("id") int id,
                              @RequestParam("price") String price,
                              @RequestParam("describe") String describe,
                              @RequestPart("photos") MultipartFile [] photos,
                              HttpSession session,
                              Model model){
        Goods goods=goodsService.getById(id);
        User user= (User) session.getAttribute("User");
        goods.setPrice(goods.getRealPrice());//老价格变成新价格
        goods.setName(name);
        goods.setUserId(user.getId());
        goods.setDescrible(describe);
        goods.setRealPrice(Float.valueOf(price));
        goods.setPolishTime(LocalDateTime.now().toString());//更新发布时间
        goodsService.updateById(goods);
        model.addAttribute("msg","添加成功！！");
        model.addAttribute("url","goodstable.html");
        return "userback/tips";
    }

}