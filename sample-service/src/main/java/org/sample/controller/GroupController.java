package org.sample.controller;

import org.sample.api.GroupService;
import org.sample.mapper.GroupMapper;
import org.sample.model.APIResult;
import org.sample.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController implements GroupService {

    @Autowired
    private GroupMapper groupDao;

    /**
     * 使用GET方法 通过URL获得数据
     */
    public APIResult<Group> simpleCreate(@RequestParam String name) {
        Group group = new Group();
        group.setName(name);
        groupDao.insert(group);
        return new APIResult(group);
    }

    /**
     * 使用POST方法 接受报文中的JSON
     */
    public APIResult<Group> create(@RequestBody Group group) {
        groupDao.insert(group);
        return new APIResult(group);
    }

    public APIResult<Group> getGroup(@RequestParam Integer id) {
        Group group = groupDao.selectById(id);
        return new APIResult(group);
    }
}