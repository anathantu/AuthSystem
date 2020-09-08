package com.hust.rbacbackend.service.api;

import com.hust.rbacbackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


//public interface UserService  extends UserDetailsService {
public interface UserService {

    public User queryUser(Integer uid);

    public void updateUser(User user);

    public User loadUserByUsername(String username);

    public void delRoles(Integer id,List<Integer> roleIdList);

    public void delRoles(User user);

    public void addRoles(Integer id,List<Integer> roleIdList);

    public List<User> queryAllUsers();
}
