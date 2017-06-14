package org.sample.seckill.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.seckill.Application;
import org.sample.seckill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void update() {
        User user = mapper.selectById(100);
        System.out.println(user.getUsername());
    }

}