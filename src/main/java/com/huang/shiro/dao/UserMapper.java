package com.huang.shiro.dao;


import com.huang.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
//
//    List<User> getAll();
//
//    void deleteUser(int id);

    User getUserById(int id);

    User getUserByUsername(String username);

//    void updateUser(User user);
//
//    void insertUser(User user);


}
