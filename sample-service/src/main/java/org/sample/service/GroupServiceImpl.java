package org.sample.service;

import org.sample.api.GroupService;
import org.sample.mapper.GroupMapper;
import org.sample.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("group")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupDao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    /** 使用GET方法 通过URL获得数据 */
    public Group simpleCreate(@RequestParam String name) {
        Group group = new Group();
        group.setName(name);
        groupDao.insert(group);
        return group;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    /** 使用POST方法 接受报文中的JSON */
    public Group create(@RequestBody Group group) {
        groupDao.insert(group);
        return group;
    }

    @RequestMapping(value = "get")
    @ResponseBody
    public Group getGroup(@RequestParam Integer id) {
        Group group = groupDao.selectById(id);
        return group;
    }
}