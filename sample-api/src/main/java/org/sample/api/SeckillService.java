package org.sample.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.dto.Exposer;
import org.sample.model.APIResult;
import org.sample.model.Pagination;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(description = "seckill")
@RequestMapping(value = "seckill", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface SeckillService {

    @ApiOperation(value = "查询")
    @RequestMapping(value = "list/{index}", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Pagination<Seckill>> search(@PathVariable("index") Integer index);

    @ApiOperation(value = "详情")
    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Seckill> detail(@PathVariable("seckillId") Integer seckillId);

    @ApiOperation(value = "查询是否开启")
    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.POST)
    @ResponseBody
    APIResult<Exposer> exposer(@PathVariable("seckillId") Integer seckillId);

    @ApiOperation(value = "执行秒杀")
    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    APIResult<SeckillRecord> execute(@PathVariable("seckillId") Integer seckillId, @PathVariable("md5") String md5,
                                     @RequestParam("userMobile") String userMobile);
//    @CookieValue(value = "userMobile", required = false) String userMobile);

}