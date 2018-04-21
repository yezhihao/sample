package org.sample.seckill.service.impl;

import org.sample.commons.lang.HashUtils;
import org.sample.seckill.service.UserService;
import org.sample.seckill.mapper.GroupMapper;
import org.sample.seckill.mapper.UserMapper;
import org.sample.seckill.model.entity.Group;
import org.sample.seckill.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Transactional
    @Override
    public void register(User user) {
        user.setPassword(HashUtils.md5(user.getPassword()));

        Group group = user.getGroup();
        if (group != null) {
            groupMapper.insert(group);
            user.setGroupId(group.getId());
        }
        userMapper.insert(user);
    }

    @Override
    public User login(String username, String password, String salt) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            password = HashUtils.md5(password);
            if (!password.equalsIgnoreCase(user.getPassword()))
                return null;

            user.setPassword(HashUtils.hashpw(user.getPassword() + (salt == null ? "" : salt)));
        }
        return user;
    }

    @Override
    public User loginByToken(String username, String token, String salt) {
        User user = userMapper.selectByUsername(username);
        if (user != null)
            if (!HashUtils.checkpw(user.getPassword() + (salt == null ? "" : salt), token))
                return null;
        return user;
    }
}