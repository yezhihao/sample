package org.sample.manager;

import org.sample.dto.Exposer;
import org.sample.model.SeckillRecord;

public interface SeckillManager {

    Exposer exportSeckillUrl(int seckillId);

    SeckillRecord executeSeckill(int seckillId, String userMobile, String md5);

    SeckillRecord executeSeckillProcedure(int seckillId, String userMobile, String md5);
}
