package org.sample.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.sample.model.SeckillRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class SeckillRecordMapperTest {

    @Autowired
    private SeckillRecordMapper mapper;

    @Test
    public void insert() {
        SeckillRecord record = new SeckillRecord();
        record.setSeckillId(1);
        record.setUserMobile("182117341802");
        int row = mapper.insert(record);
        assert row == 0;
    }

    @Test
    public void selectById() {
        SeckillRecord record = mapper.selectById(1, "182117341802");
        System.out.println(record);
        Assert.assertNotNull(record.getSeckillId());
    }

    /*
     * @Test public int insertRequired() { return 0; }
     * @Test public int update() { return 0; }
     * @Test public int updateById() { return 0; }
     */

}