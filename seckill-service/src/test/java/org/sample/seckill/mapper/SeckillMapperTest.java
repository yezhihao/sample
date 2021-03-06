package org.sample.seckill.mapper;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.component.mybatis.Page;
import org.sample.model.Pagination;
import org.sample.model.PageInfo;
import org.sample.seckill.model.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
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
        Pagination result = Page.start(() -> mapper.select(record), new PageInfo(1, 10));
        log.info(result.toString());
        Assert.assertTrue(!result.getList().isEmpty());
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
