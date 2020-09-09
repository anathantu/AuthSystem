package com.hust.rbacbackend.security;

import com.hust.rbacbackend.entity.User;
import com.hust.rbacbackend.service.api.UserService;
import com.hust.rbacbackend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    private String header = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(header);

        if (!StringUtils.isEmpty(token)) {

            Boolean check = null;
            try {
                check = jwtUtils.isTokenExpired(token);
            } catch (Exception e) {
                new Throwable("令牌已过期，请重新登录" + e.getMessage());
            }

            if (!check) {
                String username = jwtUtils.getUsernameFromToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    User user = userService.loadUserByUsername(username);
                    boolean validata = false;
                    try {

                        validata = jwtUtils.validateToken(token,user);

                    } catch (Exception e) {
                        new Throwable("验证token无效:" + e.getMessage());
                    }
                    if (validata) {
                        // 将用户信息存入 authentication，方便后续校验
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        user.getAuthorities()
                                );
                        //
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
