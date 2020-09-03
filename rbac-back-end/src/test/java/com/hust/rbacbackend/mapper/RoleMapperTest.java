package com.hust.rbacbackend.mapper;

import com.hust.rbacbackend.RbacBackEndApplication;
import com.hust.rbacbackend.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RbacBackEndApplication.class)
public class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;

    @Test
    public void testLoadRolesByUid(){
        List<Role> roles = roleMapper.loadRolesByUid(1);
        for(Role r:roles){
            System.out.println(r);
        }
    }

    @Test
    public void testAddRole(){
        Role r=new Role();
        r.setRoleName("ROLE_ADMIN");
        roleMapper.addRole(r);
    }
}
