package com.huang.shiro.controller;

import com.huang.shiro.service.ShoppingCarService;
import com.huang.shiro.vo.ShoppingCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-09 11:11
 */
@Controller
public class ShoppingCarController {

    @Autowired
    ShoppingCarService shoppingCarService;


    @RequestMapping("/shoppingcar/{userId}")
    public String list(@PathVariable("userId") int userId, Model model) {
        List<ShoppingCarVo> shoppingCarVo = shoppingCarService.list(userId);
        model.addAttribute("shoppingCarVo", shoppingCarVo);
        return "shoppingcar";
    }

    @ResponseBody
    @RequestMapping("/addGoodsToSc")
    public String addGoodsToSc(int goodsId, int goodsNumber) {
        shoppingCarService.addGoodsToShoppingCar(goodsId, goodsNumber);
        return "0";
    }

    @ResponseBody
    @RequestMapping("/pay")
    public String pay() {
        return shoppingCarService.pay();
    }

}
