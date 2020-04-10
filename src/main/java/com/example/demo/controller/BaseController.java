package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;

public class BaseController {

    @Autowired
    private ServletRequest servletRequest;

    public User getUser() {
        System.out.println(servletRequest);
        User user = (User) servletRequest.getAttribute("USER_SESSION");
        return user;
    }
}
