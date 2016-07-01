package org.sample.manager;

import org.junit.Assert;
import org.junit.Test;
import org.sample.dto.Exposer;
import org.sample.model.SeckillRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SeckillManagerTest {

    private static final Logger log = LoggerFactory.getLogger(SeckillManagerTest.class.getSimpleName());

    @Autowired
    private SeckillManager manager;

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