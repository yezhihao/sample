package org.sample.seckill.service.impl;

import org.sample.seckill.service.UserService;
import org.sample.seckill.mapper.GroupMapper;
import org.sample.seckill.mapper.UserMapper;
import org.sample.seckill.model.Group;
import org.sample.seckill.model.User;
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
        Group group = user.getGroup();
        if (group != null) {
            groupMapper.insert(group);
            user.setGroupId(group.getId());
        }
        userMapper.insert(user);
    }

}