package com.mumu.mumumall.controller;

import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/test/{id}")
    @ResponseBody
    public String userPage(@PathVariable("id") Integer userId) {
        User user = userService.getUserById(userId);
        return user.toString();
    }

    @PostMapping("/register")
    public ApiRestResponse register(@RequestParam("username") String username, @RequestParam("password") String password) throws MallException {
        if (ObjectUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        userService.register(username, password);
        return ApiRestResponse.success();
    }

    @PostMapping("/login")
    public ApiRestResponse login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) throws MallException {
        if (ObjectUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(username, password);
        session.setAttribute(Constant.MALL_USER, user);
        //返回用户信息前清除密码
        user.setPassword(null);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    public ApiRestResponse updateUserInfo(@RequestParam String signature, HttpSession session) throws MallException {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if (ObjectUtils.isEmpty(currentUser)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateUserInfo(user);

        return ApiRestResponse.success();
    }

    @PostMapping("/user /logout")
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/adminLogin")
    public ApiRestResponse adminLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) throws MallException {
        if (ObjectUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(username, password);
        if (userService.checkAdminRole(user)) {
            session.setAttribute(Constant.MALL_USER, user);
            //返回用户信息前清除密码
            user.setPassword(null);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }
}
