package org.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.mapper.GroupMapper;
import org.sample.model.APIResult;
import org.sample.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "group")
@Controller
@RequestMapping(value = "group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GroupController {

    @Autowired
    private GroupMapper groupDao;

    /**
     * 使用GET方法 通过URL获得数据
     */
    @ApiOperation(value = "使用GET方法 通过URL获得数据")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<Group> simpleCreate(@RequestParam String name) {
        Group group = new Group();
        group.setName(name);
        groupDao.insert(group);
        return new APIResult(group);
    }

    /**
     * 使用POST方法 接受报文中的JSON
     */
    @ApiOperation(value = "使用POST方法 接受报文中的JSON")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Group> create(@RequestBody Group group) {
        groupDao.insert(group);
        return new APIResult(group);
    }

    @ApiOperation(value = "使用GET方法 接受报文中的JSON")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<Group> getGroup(@RequestParam Integer id) {
        Group group = groupDao.selectById(id);
        return new APIResult(group);
    }
}