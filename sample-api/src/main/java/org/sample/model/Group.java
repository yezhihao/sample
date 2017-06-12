package org.sample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group extends BaseBO {

    /** 组名 */
    private String name;

    /** 上次更新时间 */
    private Date updateTime;

    /** 用户列表 */
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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