package org.sample.mapper;

import org.junit.Test;
import org.sample.api.SimpleService;
import org.sample.api.UserService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private SimpleService service;

    @Autowired
    private UserService userservice;

    @Test
    public void update() {
        User user = mapper.selectById(100);
        System.out.println(user.getUsername());
    }

    @Test
    public void test() {
        User user = service.test("dddd");
        System.out.println(user);
    }

    @Test
    public void get() {
        User user = userservice.get(100);
        System.out.println(user);
    }

}