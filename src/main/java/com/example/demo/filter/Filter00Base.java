package com.example.demo.filter;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class Filter00Base implements Filter {

    @Resource
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = null;
        {
            //userService.getById("");
            //TODO 测试代码 模拟获取当前用户信息
            List<User> users = userService.getList();
            if (users.size() > 0) {
                user = users.get(0);
            }
        }
        servletRequest.setAttribute("USER_SESSION", user);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
