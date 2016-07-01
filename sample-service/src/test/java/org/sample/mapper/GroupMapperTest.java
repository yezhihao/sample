package org.sample.mapper;

import org.junit.runner.RunWith;
import org.sample.Application;
import org.sample.model.Group;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
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