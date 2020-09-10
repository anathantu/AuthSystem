package com.hust.rbacbackend.security;

import com.hust.rbacbackend.component.ResultInfo;
import com.hust.rbacbackend.util.JsonUtils;
import com.hust.rbacbackend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //取得账号信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=stringRedisTemplate.opsForValue().get(userDetails.getUsername());
        if(token==null){
            token=jwtUtils.generateToken(userDetails.getUsername());
            stringRedisTemplate.opsForValue().set(userDetails.getUsername(),token);
        }

        response.setHeader("Authorization", token);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        Map<String ,Object> map=new HashMap<>();
        map.put("username",userDetails.getUsername());
        map.put("auth",userDetails.getAuthorities());

        JsonUtils.WriteJson(request,response, ResultInfo.success("登录成功",map));
    }
}
