package com.huang.shiro.service;

import com.huang.shiro.dao.UserMapper;
import com.huang.shiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangwx
 * @date 2020-02-06 16:46
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
