package com.huang.shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangwx
 * @date 2020-02-08 16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    Integer id;
    String name;
    String img;
    Double price;
    String summary;
    Integer stock;
}
