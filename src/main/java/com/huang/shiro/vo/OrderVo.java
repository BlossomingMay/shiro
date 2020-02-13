package com.huang.shiro.vo;

import com.huang.shiro.pojo.Order;
import lombok.Data;

/**
 * @author huangwx
 * @date 2020-02-10 16:28
 */
@Data
public class OrderVo {
    Order order;
    String goodsImg;
    String goodsName;
}
