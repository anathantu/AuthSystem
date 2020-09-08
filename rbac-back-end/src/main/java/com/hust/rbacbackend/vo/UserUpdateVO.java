package com.hust.rbacbackend.vo;

import com.hust.rbacbackend.entity.User;

public class UserUpdateVO {

    private User user;

    private RoleIdListVO roleIdList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleIdListVO getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(RoleIdListVO roleIdList) {
        this.roleIdList = roleIdList;
    }
}
