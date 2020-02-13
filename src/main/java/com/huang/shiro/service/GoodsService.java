package com.huang.shiro.service;

import com.huang.shiro.dao.GoodsMapper;
import com.huang.shiro.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-08 16:27
 */
@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    public Goods getGoodsById(int id) {
        return goodsMapper.getGoodsById(id);
    }

    public void insert(Goods goods) {
        goodsMapper.addGoods(goods);
    }

    public List<Goods> goodsList() {
        return goodsMapper.selectAll();
    }

}
