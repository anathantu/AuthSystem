package com.hust.rbacbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.rbacbackend.component.ResultInfo;
import com.hust.rbacbackend.util.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JsonUtils.WriteJson(request,response,ResultInfo.failed("访问此资源需要完全身份验证（"+authException.getMessage()+"）！"));
    }
}
