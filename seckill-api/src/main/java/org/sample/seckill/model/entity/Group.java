package org.sample.seckill.model.entity;

import org.sample.model.BaseDO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Group extends BaseDO {

    /** 组名 */
    private String name;

    /** 上次更新时间 */
    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    /** 用户列表 */
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        users.add(user);
    }

}