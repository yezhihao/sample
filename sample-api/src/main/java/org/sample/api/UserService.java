package org.sample.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.model.APIResult;
import org.sample.model.Pagination;
import org.sample.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "user")
@RequestMapping("user")
public interface UserService {

    /**
     * 使用GET方法 通过URL获得数据
     */
    @ApiOperation(value = "create")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    APIResult<User> simpleCreate(String username);

    /**
     * 使用POST方法 接受报文中的JSON
     */
    @ApiOperation(value = "create")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    APIResult<User> create(User user);

    @ApiOperation(value = "get")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    APIResult<User> get(Integer id);

    @ApiOperation(value = "search")
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    APIResult<Pagination<User>> search(Integer index, Integer groupId);

    @ApiOperation(value = "search")
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    APIResult<Pagination<User>> search(User user);

}