package org.sample.web.service;

import org.sample.model.APIResult;
import org.sample.seckill.model.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(url = "${seckill-service}", name = "UserService")
public interface UserService {

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    APIResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("slat") String slat);

    @RequestMapping(value = "user/loginByToken", method = RequestMethod.POST)
    @ResponseBody
    APIResult<User> loginByToken(@RequestParam("username") String username, @RequestParam("token") String token, @RequestParam("slat") String slat);

}