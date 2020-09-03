package com.hust.rbacbackend.mapper;

import com.hust.rbacbackend.RbacBackEndApplication;
import com.hust.rbacbackend.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RbacBackEndApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAddUser(){
        User u=new User();
        u.setEmail("wgx11111@outlook.com");
        u.setNickname("wgx");
        u.setUsername("wgxwgx");
        u.setPassword("111111");
        System.out.println(u);
        userMapper.addUser(u);
    }

    @Test
    public  void testLoadByUsername(){
        System.out.println(userMapper.loadByUsername("tuchang"));
    }
}
