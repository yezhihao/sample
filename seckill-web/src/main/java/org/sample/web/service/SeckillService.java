package org.sample.web.service;

import org.sample.seckill.dto.Exposer;
import org.sample.model.APIResult;
import org.sample.model.Pagination;
import org.sample.seckill.model.Seckill;
import org.sample.seckill.model.SeckillRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${seckill-service}", name = "SeckillService")
public interface SeckillService {

    @RequestMapping(value = "seckill/list/{index}", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Pagination<Seckill>> search(@PathVariable("index") Integer index);

    @RequestMapping(value = "seckill/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    APIResult<Pagination<Seckill>> search(@RequestBody Seckill seckill);

    @RequestMapping(value = "seckill/{seckillId}/detail", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Seckill> detail(@PathVariable("seckillId") Integer seckillId);

    @RequestMapping(value = "seckill/{seckillId}/exposer", method = RequestMethod.POST)
    @ResponseBody
    APIResult<Exposer> exposer(@PathVariable("seckillId") Integer seckillId);

    @RequestMapping(value = "seckill/{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    APIResult<SeckillRecord> execute(@PathVariable("seckillId") Integer seckillId, @PathVariable("md5") String md5,
                                     @RequestParam("userMobile") String userMobile);
//    @CookieValue(value = "userMobile", required = false) String userMobile);

}