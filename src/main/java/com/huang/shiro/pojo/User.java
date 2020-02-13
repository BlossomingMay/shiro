package com.huang.shiro.pojo;

import lombok.*;

/**
 * @author huangwx
 * @date 2020-02-06 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String name;
    String pwd;
    String role;
}
