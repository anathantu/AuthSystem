package com.hust.rbacbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.rbacbackend.service.api.UserService;
import com.hust.rbacbackend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                || request.getContentType().contains(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            //取authenticationBean
            Map<String, String> authenticationBean = null;

            //用try with resource，方便自动释放资源
            try (InputStream is = request.getInputStream()) {
                authenticationBean = mapper.readValue(is, Map.class);
            } catch (IOException e) {
                //将异常放到自定义的异常类中
                throw new IllegalArgumentException(e.getMessage());
            }

            try {

                String username = authenticationBean.get(getUsernameParameter());
                String password = authenticationBean.get(getPasswordParameter());

                if (username == null) {
                    username = "";
                }
                if (password == null) {
                    password = "";
                }

                username = username.trim();
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        username, password);
                setDetails(request, authRequest);

                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }else{
            return super.attemptAuthentication(request, response);
        }
    }
}
