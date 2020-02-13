package com.huang.shiro.vo;

import com.huang.shiro.pojo.ShoppingCar;
import lombok.Data;

/**
 * @author huangwx
 * @date 2020-02-11 15:26
 */
@Data
public class ShoppingCarVo {
    ShoppingCar shoppingCar;
    String goodsName;
    Double price;
}
