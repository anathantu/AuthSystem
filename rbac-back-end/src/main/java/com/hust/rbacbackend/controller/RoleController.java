package com.hust.rbacbackend.controller;

import com.hust.rbacbackend.component.ResultInfo;
import com.hust.rbacbackend.entity.Role;
import com.hust.rbacbackend.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ResultInfo queryAllRoles(){
        List<Role> roles=roleService.queryAllRoles();
        return ResultInfo.success(200,"查询角色成功",roles);
    }
}
