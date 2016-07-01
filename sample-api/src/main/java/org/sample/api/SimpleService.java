package org.sample.api;

import org.sample.model.User;

public interface SimpleService {

    User test(String name);

    void testWarn(String message);

    void testThrow(String message) throws Exception;

    String put(String key, String value);

    String put(String key);

}