package org.sample.controller;

import java.util.List;

import org.sample.api.SeckillService;
import org.sample.dto.Exposer;
import org.sample.model.*;
import org.sample.enums.ResultCode;
import org.sample.exception.APIException;
import org.sample.manager.SeckillManager;
import org.sample.mapper.SeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeckillController implements SeckillService {

    @Autowired
    private SeckillManager manager;

    @Autowired
    private SeckillMapper mapper;

    public APIResult<Pagination<Seckill>> search(@PathVariable Integer index) {
        PageInfo pageInfo = new PageInfo(index);
        List<Seckill> list = mapper.select(new Seckill(pageInfo));
        return new APIResult(new Pagination<>(list, pageInfo));
    }

    public APIResult<Seckill> detail(@PathVariable Integer seckillId) {
        Seckill result = mapper.selectById(seckillId);
        return new APIResult(result);
    }

    public APIResult<Exposer> exposer(@PathVariable Integer seckillId) {
        Exposer result = manager.exportSeckillUrl(seckillId);
        return new APIResult(result);
    }

    public APIResult<SeckillRecord> execute(@PathVariable Integer seckillId, @PathVariable String md5,
                                            @RequestParam String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCode.S400);
        SeckillRecord result = manager.executeSeckillProcedure(seckillId, userMobile, md5);
        return new APIResult(result);
    }

}