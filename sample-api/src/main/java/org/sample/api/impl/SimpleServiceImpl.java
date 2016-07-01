package org.sample.api.impl;

import org.sample.api.SimpleService;
import org.sample.model.User;

public enum SimpleServiceImpl implements SimpleService {
    INSTANCE;

    @Override
    public User test(String name) {
        return null;
    }

    @Override
    public void testWarn(String message) {

    }

    @Override
    public void testThrow(String message) throws Exception {

    }

    @Override
    public String put(String key, String value) {

        return null;
    }

    @Override
    public String put(String key) {

        return null;
    }

}