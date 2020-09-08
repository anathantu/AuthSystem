package com.hust.rbacbackend.mapper;

import com.hust.rbacbackend.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    public List<Role> loadRolesByUid(Integer id);

    public void addRole(Role r);

    public List<Role> queryAllRoles();
}
