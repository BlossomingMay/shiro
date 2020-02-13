package com.huang.shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author huangwx
 * @date 2020-02-08 19:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Integer id;
    Integer goodsId;
    Integer userId;
    Date payTime;
    Double price;
    Integer goodsNumber;

}
