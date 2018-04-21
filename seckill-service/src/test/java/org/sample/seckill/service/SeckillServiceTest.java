package org.sample.seckill.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.seckill.Application;
import org.sample.seckill.model.vo.Exposer;
import org.sample.seckill.model.entity.SeckillRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SeckillServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SeckillServiceTest.class.getSimpleName());

    @Autowired
    private SeckillService manager;

    @Test
    public void testExecuteSeckill() {
        Exposer exposer = manager.exportSeckillUrl(1);
        SeckillRecord seckillRecord = manager.executeSeckill(1, "182173418044", exposer.getMd5());
        log.info(seckillRecord.toString());
        Assert.assertNotNull(seckillRecord);
    }

    @Test
    public void testExecuteSeckillProcedure() {
        Exposer exposer = manager.exportSeckillUrl(1);
        SeckillRecord seckillRecord = manager.executeSeckillProcedure(1, "182173418047", exposer.getMd5());
        log.info(seckillRecord.toString());
        Assert.assertNotNull(seckillRecord);
    }

}