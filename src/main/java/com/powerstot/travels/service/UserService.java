package com.powerstot.travels.service;

import com.powerstot.travels.entity.User;

public interface UserService {

    User login(User user);

    void register(User user);
}
