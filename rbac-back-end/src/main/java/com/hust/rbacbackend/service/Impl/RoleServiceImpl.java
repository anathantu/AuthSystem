package com.hust.rbacbackend.service.Impl;

import com.hust.rbacbackend.entity.Role;
import com.hust.rbacbackend.mapper.RoleMapper;
import com.hust.rbacbackend.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }
}
