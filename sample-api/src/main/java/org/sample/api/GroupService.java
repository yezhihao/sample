package org.sample.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.model.APIResult;
import org.sample.model.Group;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(description = "group")
@RequestMapping(value = "group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface GroupService {

    /**
     * 使用GET方法 通过URL获得数据
     */
    @ApiOperation(value = "使用GET方法 通过URL获得数据")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Group> simpleCreate(@RequestParam String name);

    /**
     * 使用POST方法 接受报文中的JSON
     */
    @ApiOperation(value = "使用POST方法 接受报文中的JSON")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    APIResult<Group> create(@RequestBody Group group);

    @ApiOperation(value = "使用GET方法 接受报文中的JSON")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Group> getGroup(@RequestParam Integer id);

}