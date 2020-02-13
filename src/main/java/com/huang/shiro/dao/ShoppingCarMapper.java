package com.huang.shiro.dao;

import com.huang.shiro.pojo.ShoppingCar;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-08 22:01
 */


public interface ShoppingCarMapper extends Mapper<ShoppingCar> {
    void deleteByGoodsIdUserId(int userId);

    List<ShoppingCar> selectByUserId(int userId);
}
