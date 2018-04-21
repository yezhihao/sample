package org.sample.seckill.mapper;

import org.junit.runner.RunWith;
import org.sample.seckill.Application;
import org.sample.seckill.model.entity.Group;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class GroupMapperTest {

    public int deleteById(Long id) {

        return 0;
    }

    public int insert(Group record) {

        return 0;
    }

    public int insertRequired(Group record) {

        return 0;
    }

    public Group selectById(Long id) {

        return null;
    }

    public int update(Group record) {

        return 0;
    }

    public int updateById(Group record) {

        return 0;
    }

}