package org.sample.api;

import org.sample.dto.Exposer;
import org.sample.model.PaginationResult;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;

public interface SeckillService {

    PaginationResult<Seckill> search(Integer index);

    Seckill detail(Integer seckillId);

    Exposer exposer(Integer seckillId);

    SeckillRecord execute(Integer seckillId, String md5, String userMobile);

}