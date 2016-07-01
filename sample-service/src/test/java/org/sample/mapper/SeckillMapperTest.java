package org.sample.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.Application;
import org.sample.entity.Pagination;
import org.sample.model.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SeckillMapperTest {

    private static final Logger log = LoggerFactory.getLogger(SeckillMapperTest.class.getSimpleName());

    @Autowired
    private SeckillMapper mapper;

    @Test
    public void testDeleteById() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsert() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectById() {
        Seckill seckill = mapper.selectById(1);
        Assert.assertNotNull(seckill);
    }

    @Test
    public void testSelect() {
        Seckill record = new Seckill();
        record.setPagination(new Pagination(1, 1));
        List<Seckill> result = mapper.select(record);
        log.info(result.toString());
        Assert.assertTrue(!result.isEmpty());
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testReduceCount() {
        fail("Not yet implemented");
    }

}
