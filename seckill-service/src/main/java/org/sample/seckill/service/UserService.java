package org.sample.seckill.service;

import org.sample.seckill.model.User;

public interface UserService {

    void register(User user);

    User login(String username, String password, String slat);

    User loginByToken(String username, String token, String slat);

}