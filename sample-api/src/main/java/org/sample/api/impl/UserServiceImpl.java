package org.sample.api.impl;

import org.sample.api.UserService;
import org.sample.model.PaginationResult;
import org.sample.model.User;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    @Override
    public User simpleCreate(String username) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User get(Integer id) {
        return null;
    }

    @Override
    public PaginationResult<User> search(Integer index, Integer groupId) {
        return null;
    }

    @Override
    public PaginationResult<User> search(User user) {
        return null;
    }

}