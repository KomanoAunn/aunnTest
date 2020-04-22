package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.param.QueryUser;
import com.example.demo.model.param.UserAddParam;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping("/my1")
    public String my1() {
        User user = this.getUser();
        if (user == null) {
            return "not Login";
        }
        return user.getId();
    }

    @PostMapping("/register")
    public String register(UserAddParam userAddParam) {
        userService.doRegister(userAddParam);
        return "";
    }

    @GetMapping("/list")
    public String list() {
        List<User> list = userService.getList();
        if (list == null) {
            return "";
        }
        return list.toString();
    }

    @GetMapping("/{id}")
    public String info(@PathVariable String id) {
        User user = userService.getById(id);
        if (user == null) {
            return "";
        }
        return user.toString();
    }

    @GetMapping("/query")
    public String query(QueryUser queryUser){
        return userService.getQueryList(queryUser).toString();
    }
}
