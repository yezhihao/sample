package org.sample.seckill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.component.mybatis.Page;
import org.sample.model.*;
import org.sample.model.exception.APIException;
import org.sample.seckill.model.entity.Seckill;
import org.sample.seckill.model.entity.SeckillRecord;
import org.sample.seckill.model.enums.ResultCodes;
import org.sample.seckill.model.vo.Exposer;
import org.sample.seckill.service.SeckillService;
import org.sample.seckill.mapper.SeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "seckill")
@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SeckillController {

    @Autowired
    private SeckillService manager;

    @Autowired
    private SeckillMapper mapper;

    @ApiOperation(value = "查询")
    @RequestMapping(value = "seckill/list/{index}", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<Pagination<Seckill>> search(@PathVariable Integer index) {
        Pagination result = Page.start(() -> mapper.select(new Seckill()), new PageInfo(index));
        return new APIResult(result);
    }

    @ApiOperation(value = "查询")
    @RequestMapping(value = "seckill/list", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Pagination<Seckill>> search(@RequestBody Seckill seckill, PageInfo pageInfo) {
        Pagination result = Page.start(() -> mapper.select(seckill), pageInfo);
        return new APIResult(result);
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value = "seckill/{seckillId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<Seckill> detail(@PathVariable Integer seckillId) {
        Seckill result = mapper.selectById(seckillId);
        return new APIResult(result);
    }

    @ApiOperation(value = "查询是否开启")
    @RequestMapping(value = "seckill/{seckillId}/exposer", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Exposer> exposer(@PathVariable Integer seckillId) {
        Exposer result = manager.exportSeckillUrl(seckillId);
        return new APIResult(result);
    }

    @ApiOperation(value = "执行秒杀")
    @RequestMapping(value = "seckill/{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<SeckillRecord> execute(@PathVariable Integer seckillId, @PathVariable String md5,
                                            @RequestParam String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCodes.S400);
//        SeckillRecord result = manager.executeSeckillProcedure(seckillId, userMobile, md5);
        SeckillRecord result = manager.executeSeckill(seckillId, userMobile, md5);
        return new APIResult(result);
    }

}