package org.sample.service;

import org.sample.dto.Exposer;
import org.sample.model.SeckillRecord;

public interface SeckillService {

    Exposer exportSeckillUrl(int seckillId);

    SeckillRecord executeSeckill(int seckillId, String userMobile, String md5);

    SeckillRecord executeSeckillProcedure(int seckillId, String userMobile, String md5);
}
