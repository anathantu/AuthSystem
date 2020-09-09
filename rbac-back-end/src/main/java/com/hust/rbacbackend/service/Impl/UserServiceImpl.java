package com.hust.rbacbackend.service.Impl;

import com.hust.rbacbackend.entity.Role;
import com.hust.rbacbackend.entity.User;
import com.hust.rbacbackend.mapper.RoleMapper;
import com.hust.rbacbackend.mapper.UserMapper;
import com.hust.rbacbackend.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public User queryUser(Integer uid) {
        User user = userMapper.queryUser(uid);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleMapper.loadRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        if(username==null){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        User user = userMapper.loadByUsername(username);
        if(user==null){
            throw new IllegalArgumentException("用户不存在");
        }
        List<Role> roles = roleMapper.loadRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public void delRoles(Integer id, List<Integer> roleIdList) {
        User user = userMapper.queryUser(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        for (Integer roleId : roleIdList) {
            userMapper.delRole(id, roleId);
        }
    }

    @Override
    public void delRoles(User user) {
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        List<Role> roles = user.getRoles();
        Integer id = user.getId();
        for (Role r : roles) {
            userMapper.delRole(id, r.getId());
        }
    }

    public void addRoles(Integer id, List<Integer> roleIdList) {
        User user = userMapper.queryUser(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        userMapper.addRole(id, roleIdList);
    }

    @Override
    public List<User> queryAllUsers() {
        List<User> users = userMapper.queryAllUsers();

        for (User u : users) {
            List<Role> roles = roleMapper.loadRolesByUid(u.getId());
            u.setRoles(roles);
        }

        return users;
    }
}
