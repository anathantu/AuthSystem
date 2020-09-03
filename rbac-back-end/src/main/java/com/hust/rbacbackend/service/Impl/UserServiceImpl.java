package com.hust.rbacbackend.service.Impl;

import com.hust.rbacbackend.entity.Role;
import com.hust.rbacbackend.entity.User;
import com.hust.rbacbackend.mapper.RoleMapper;
import com.hust.rbacbackend.mapper.UserMapper;
import com.hust.rbacbackend.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.loadByUsername(username);
//        List<Role> roles=roleMapper.loadRolesByUid(user.getId());
//        user.setRoles(roles);
//        return user;
//    }


    @Override
    public User queryUser(Integer uid) {
        User user=userMapper.queryUser(uid);
        if(user==null){
            return null;
        }
        List<Role> roles=roleMapper.loadRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        User user = userMapper.loadByUsername(username);
        List<Role> roles=roleMapper.loadRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }
}
