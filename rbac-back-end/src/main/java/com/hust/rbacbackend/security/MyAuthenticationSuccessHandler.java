package com.hust.rbacbackend.security;

import com.hust.rbacbackend.util.JsonUtils;
import com.hust.rbacbackend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //取得账号信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("userDetails = " + userDetails);

        String token=jwtUtils.generateToken(userDetails.getUsername());

        Map<String ,Object> map=new HashMap<>();
        map.put("username",userDetails.getUsername());
        map.put("auth",userDetails.getAuthorities());
        map.put("token",token);
        JsonUtils.WriteJson(request,response,token);
    }
}
