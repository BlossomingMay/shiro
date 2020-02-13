package com.huang.shiro.dao;

import com.huang.shiro.pojo.Goods;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author huangwx
 * @date 2020-02-08 16:21
 */

public interface GoodsMapper extends Mapper<Goods> {
    Goods getGoodsById(int id);

    void addGoods(Goods goods);
}
