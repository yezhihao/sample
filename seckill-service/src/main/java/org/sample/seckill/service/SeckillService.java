package org.sample.seckill.service;

import org.sample.seckill.dto.Exposer;
import org.sample.seckill.model.SeckillRecord;

public interface SeckillService {

    Exposer exportSeckillUrl(int seckillId);

    SeckillRecord executeSeckill(int seckillId, String userMobile, String md5);

    SeckillRecord executeSeckillProcedure(int seckillId, String userMobile, String md5);
}
