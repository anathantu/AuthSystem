package com.hust.rbacbackend.service.api;

import com.hust.rbacbackend.entity.User;
import org.springframework.stereotype.Service;


//public interface UserService  extends UserDetailsService {
public interface UserService {

    public User queryUser(Integer uid);

    public void updateUser(User user);

    public User loadUserByUsername(String username);
}
