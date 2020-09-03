package com.hust.rbacbackend.controller;

import com.hust.rbacbackend.component.ResultInfo;
import com.hust.rbacbackend.entity.User;
import com.hust.rbacbackend.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/{uid}/roles")
    public ResultInfo addRoles(@PathVariable("uid") Integer id){

        return ResultInfo.success(200,"post请求成功",null);
    }

    @GetMapping("/{uid}")
    public ResultInfo queryUser(@PathVariable("uid") Integer uid){
        if(uid==null){
            throw new IllegalArgumentException("用户名不能为空");
        }
        User user = userService.queryUser(uid);
        if(user==null){
            throw new IllegalArgumentException("用户不存在");
        }
        return ResultInfo.success(200,"查询成功",user);
    }

    public ResultInfo removeRoles(){
        return null;
    }

    @PutMapping("/{uid}")
    public ResultInfo updateUser(@PathVariable("uid") Integer id,@RequestBody User user){
        if(user==null){
            throw new IllegalArgumentException("请填写用户信息");
        }
        user.setId(id);
        userService.updateUser(user);
        return ResultInfo.success(200,"更新成功",null);
    }
}
