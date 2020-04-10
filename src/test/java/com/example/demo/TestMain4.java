package com.example.demo;

import com.example.demo.model.base.BaseModel;
import com.example.demo.model.User;

public class TestMain4 {
    public static void main(String[] args) {
        User user=new User();
        System.out.println(user instanceof  User);
        System.out.println(user instanceof BaseModel);
        System.out.println(((BaseModel)user).getId());
    }
}
