package com.powerstot.travels.dao;

import com.powerstot.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    //根据据用户名查询用户
    //也可以用于登录逻辑
    User findByUsername(String username);

    //注册用户
    void save(User user);
}
