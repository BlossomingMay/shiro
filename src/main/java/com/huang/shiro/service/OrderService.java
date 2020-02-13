package com.huang.shiro.service;

import com.huang.shiro.dao.GoodsMapper;
import com.huang.shiro.dao.OrderMapper;
import com.huang.shiro.pojo.Goods;
import com.huang.shiro.pojo.Order;
import com.huang.shiro.pojo.User;
import com.huang.shiro.vo.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-08 19:48
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public List<OrderVo> getAllOrderById(int id) {
        List<Order> temp = orderMapper.getAllOrderById(id);
        List<OrderVo> list = new LinkedList<>();

        for (Order order : temp) {
            Goods goods = goodsMapper.getGoodsById(order.getGoodsId());
            OrderVo orderVo = new OrderVo();
            orderVo.setOrder(order);
            orderVo.setGoodsImg(goods.getImg());
            orderVo.setGoodsName(goods.getName());
            list.add(orderVo);
        }

        return list;

    }

    public void addOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    public List<Integer> getBuyedGoodsId() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user == null) {
            return null;
        }
        return orderMapper.getBuyedGoodsId(user.getId());
    }

    public List<Integer> getSaledGoodsId() {
        return orderMapper.getSaledGoodsId();
    }


}
