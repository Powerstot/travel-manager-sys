package com.powerstot.test;

import com.powerstot.travels.TravelsApplication;
import com.powerstot.travels.entity.User;
import com.powerstot.travels.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserSerivce {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest() {
        User user = new User();
        user.setUsername("powers");
        user.setPassword("powers");
        user.setEmail("powers@123.com");
        userService.register(user);
    }
}
