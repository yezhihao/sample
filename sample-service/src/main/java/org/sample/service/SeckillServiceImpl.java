package org.sample.service;

import java.util.List;

import org.sample.api.SeckillService;
import org.sample.dto.Exposer;
import org.sample.model.PageInfo;
import org.sample.model.PaginationResult;
import org.sample.enums.ResultCode;
import org.sample.exception.APIException;
import org.sample.manager.SeckillManager;
import org.sample.mapper.SeckillMapper;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("seckill")
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillManager manager;

    @Autowired
    private SeckillMapper mapper;

    @RequestMapping(value = "list/{index}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PaginationResult<Seckill> search(@PathVariable Integer index) {
        PageInfo pageInfo = new PageInfo(index);
        List<Seckill> list = mapper.select(new Seckill(pageInfo));
        return new PaginationResult<>(list, pageInfo);
    }

    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Seckill detail(@PathVariable Integer seckillId) {
        Seckill result = mapper.selectById(seckillId);
        return result;
    }

    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Exposer exposer(@PathVariable Integer seckillId) {
        Exposer result = manager.exportSeckillUrl(seckillId);
        return result;
    }

    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public SeckillRecord execute(@PathVariable Integer seckillId, //
            @PathVariable String md5, //
            @CookieValue(value = "userMobile", required = false) String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCode.S400);
        SeckillRecord result = manager.executeSeckillProcedure(seckillId, userMobile, md5);
        return result;
    }

    @RequestMapping(value = "{seckillId}/{md5}/{userMobile}/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public SeckillRecord testExecute(@PathVariable Integer seckillId, //
            @PathVariable String md5, //
            @PathVariable String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCode.S400);
        SeckillRecord result = manager.executeSeckillProcedure(seckillId, userMobile, md5);
        return result;
    }
}