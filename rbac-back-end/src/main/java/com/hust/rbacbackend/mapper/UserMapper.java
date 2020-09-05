package com.hust.rbacbackend.mapper;

import com.hust.rbacbackend.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public void addUser(User user);

    public User loadByUsername(String username);

    public void updateUser(User user);

    //暂定为用id查询
    public User queryUser(Integer uid);

    public void delRole(@Param("uid") Integer uid, @Param("roleId") Integer roleId);

    public void addRole(@Param("uid") Integer uid,@Param("roleIdList") List<Integer> roleIdList);
}
