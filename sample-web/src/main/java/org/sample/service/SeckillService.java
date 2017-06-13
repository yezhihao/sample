package org.sample.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by alan on 2017/6/13.
 */
@FeignClient(url = "${seckill-service}", name = "SeckillService")
public interface SeckillService extends org.sample.api.SeckillService {

}