package com.huang.shiro.controller;

import com.huang.shiro.pojo.Goods;
import com.huang.shiro.service.GoodsService;

import com.huang.shiro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author huangwx
 * @date 2020-02-08 19:01
 */
@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Integer> buyedGoods = orderService.getBuyedGoodsId();
        model.addAttribute("buyedGoods", buyedGoods);

        List<Integer> saledGoods = orderService.getSaledGoodsId();
        model.addAttribute("saledGoods", saledGoods);

        List<Goods> goods = goodsService.goodsList();
        model.addAttribute("goodsList", goods);
        return "home";
    }

    @RequestMapping("/goods/add")
    public String addingPage() {
        return "add";
    }

    @RequestMapping("/goodsDetail/{id}")
    public String goodsDetail(@PathVariable("id") int id, Model model) {
        model.addAttribute("goods", goodsService.getGoodsById(id));
        return "goodsDetail";
    }

    @RequestMapping("/addGoods")
    public String addGoods(@RequestParam(required = true, value = "title") String name,
                           @RequestParam(required = true, value = "abstract") String summary,
                           @RequestParam(required = true, value = "file") MultipartFile file,
                           @RequestParam(required = true, value = "infotext") String article,
                           @RequestParam(required = true, value = "price") Double price,
                           Model model) {

        if (name.equals("") || file == null || article.equals("") || price == null || summary.equals("")) {
            model.addAttribute("msg", "检查是否输入错误或为空!!!");
            return "add";
        }

        Goods goods = new Goods();
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File targetFile = new File(path, newFileName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(targetFile);
                goods.setImg("/img/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("msg", "图片上传失败!!!");
                return "add";
            }
        }

        goods.setName(name);
        goods.setPrice(price);
        goods.setSummary(article);
        goodsService.insert(goods);
        model.addAttribute("msg", "成功添加!!!");
        return "add";
    }

}
