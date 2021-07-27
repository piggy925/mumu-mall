package com.mumu.mumumall.controller;

import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/test/{id}")
    @ResponseBody
    public String userPage(@PathVariable("id") Integer userId) {
        User user = userService.getUserById(userId);
        return user.toString();
    }
}
