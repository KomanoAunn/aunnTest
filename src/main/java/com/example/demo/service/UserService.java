package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.param.UserAddParam;

import java.util.List;

public interface UserService {

    User doRegister(UserAddParam userAddParam);

    List<User> getList();

    User getById(String id);
}
