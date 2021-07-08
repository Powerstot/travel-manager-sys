package com.powerstot.travels.service;

import com.powerstot.travels.dao.UserDao;
import com.powerstot.travels.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User userDB = userDao.findByUsername(user.getUsername());
        log.info("前台的user" + user);
        log.info("查到的user" + userDB);
        if (userDB != null) {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }
            throw new RuntimeException("密码错误!");
        } else {
            throw new RuntimeException("用户名错误!");
        }
    }

    @Override
    public void register(User user) {
        if (userDao.findByUsername(user.getUsername()) == null) {
            userDao.save(user);
        } else {
            throw new RuntimeException("用户名已存在!");
        }
    }
}
