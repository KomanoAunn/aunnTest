package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.model.param.UserAddParam;
import com.example.demo.service.UserService;
import com.example.demo.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User doRegister(UserAddParam userAddParam) {
        User user = new User();
        BeanUtils.copyProperties(userAddParam, user);
        //TODO 暂无加密
        //自动生成ID
        user.setId(UUIDUtils.get62RadixId());
        userDao.insert(user);
        return user;
    }

    @Override
    public List<User> getList() {
        return userDao.getList();
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }
}
