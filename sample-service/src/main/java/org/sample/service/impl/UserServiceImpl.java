package org.sample.service.impl;

import org.sample.service.UserService;
import org.sample.mapper.GroupMapper;
import org.sample.mapper.UserMapper;
import org.sample.model.Group;
import org.sample.model.User;
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