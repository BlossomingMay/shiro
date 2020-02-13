package com.huang.shiro.dao;

import com.huang.shiro.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-08 19:16
 */
@Mapper
@Repository
public interface OrderMapper {
    List<Order> getAllOrderById(int id);

    Order getOrderById(int id);

    void insertOrder(Order order);

    List<Integer> getBuyedGoodsId(int userId);

    List<Integer> getSaledGoodsId();
}
