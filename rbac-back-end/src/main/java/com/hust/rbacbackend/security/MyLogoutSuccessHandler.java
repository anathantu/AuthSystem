package com.hust.rbacbackend.security;

import com.hust.rbacbackend.component.ResultInfo;
import com.hust.rbacbackend.util.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("MyLogoutSuccessHandler:退出成功。。。。。。");
        System.out.println(response.getHeader("Authorization"));
        JsonUtils.WriteJson(request,response, ResultInfo.success("注销成功"));
    }
}
