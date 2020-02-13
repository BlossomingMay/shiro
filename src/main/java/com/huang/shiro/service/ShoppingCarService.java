package com.huang.shiro.service;

import com.huang.shiro.dao.ShoppingCarMapper;
import com.huang.shiro.pojo.Goods;
import com.huang.shiro.pojo.Order;
import com.huang.shiro.pojo.ShoppingCar;
import com.huang.shiro.pojo.User;
import com.huang.shiro.vo.ShoppingCarVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-09 11:09
 */

@Service
public class ShoppingCarService {

    @Autowired
    ShoppingCarMapper shoppingCarMapper;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    public List<ShoppingCarVo> list(int userId) {
        List<ShoppingCar> list = shoppingCarMapper.selectByUserId(userId);
        List<ShoppingCarVo> ret = new LinkedList<>();
        for (ShoppingCar sc : list) {
            ShoppingCarVo sv = new ShoppingCarVo();
            sv.setShoppingCar(sc);
            Goods tem = goodsService.getGoodsById(sc.getGoodsId());
            String goodsName = tem.getName();
            Double price = tem.getPrice();

            sv.setGoodsName(goodsName);
            sv.setPrice(price);
            ret.add(sv);
        }

        return ret;

    }

    public List<ShoppingCar> test() {
        return shoppingCarMapper.selectByUserId(2);
    }

    public void addGoodsToShoppingCar(int goodsId, int goodsNumber) {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setGoodsId(goodsId);
        shoppingCar.setGoodsNumber(goodsNumber);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        shoppingCar.setUserId(user.getId());
        shoppingCarMapper.insert(shoppingCar);
    }

    public void deleteGoodsFromShoppingCar(int userId) {
        shoppingCarMapper.deleteByGoodsIdUserId(userId);
    }

    public String pay() {
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        int userId = currentUser.getId();
        List<ShoppingCar> list = shoppingCarMapper.selectByUserId(userId);
        if (!list.isEmpty()) {
            for (ShoppingCar sc : list) {
                Order order = new Order();
                order.setGoodsId(sc.getGoodsId());
                order.setGoodsNumber(sc.getGoodsNumber());
                order.setPrice(goodsService.getGoodsById(sc.getGoodsId()).getPrice());
                order.setUserId(userId);
                Date date = new Date(System.currentTimeMillis());
                order.setPayTime(date);
                System.out.println(date.toString());
                orderService.addOrder(order);
            }
            deleteGoodsFromShoppingCar(userId);
            return "0";
        } else {
            return "1";
        }
    }

}
