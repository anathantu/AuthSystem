package com.hust.rbacbackend.mapper;

import com.hust.rbacbackend.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public void addUser(User user);

    public User loadByUsername(String username);

    public void updateUser(User user);

    //暂定为用id查询
    public User queryUser(Integer uid);
}
