package org.sample.api.impl;

import org.sample.api.GroupService;
import org.sample.commons.io.JsonUtils;
import org.sample.commons.net.HttpClient;
import org.sample.model.Group;

public enum GroupServiceImpl implements GroupService {
    INSTANCE;

    private HttpClient client = HttpClient.getInstance("server.seckill");

    /** 使用GET方法 通过URL获得数据 */
    public Group simpleCreate(String name) {
        String response = client.get("group/create", "name", name);
        return JsonUtils.toObj(response, Group.class);
    }

    /** 使用POST方法 接受报文中的JSON */
    public Group create(Group group) {
        String response = client.post("group/create", group);
        return JsonUtils.toObj(response, Group.class);
    }

    public Group getGroup(Integer id) {
        String response = client.get("group/get", String.valueOf(id));
        return JsonUtils.toObj(response, Group.class);
    }
}