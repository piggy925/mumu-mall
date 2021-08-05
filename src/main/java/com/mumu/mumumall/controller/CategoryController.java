package com.mumu.mumumall.controller;

import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.model.request.addCategoryReq;
import com.mumu.mumumall.service.CategoryService;
import com.mumu.mumumall.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class CategoryController {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;

    @PostMapping("admin/category/add")
    public ApiRestResponse addCategory(HttpSession session, @RequestBody addCategoryReq addCategoryReq) {
        if (addCategoryReq.getName() == null || addCategoryReq.getType() == null || addCategoryReq.getOrderNum() == null || addCategoryReq.getParentId() == null) {
            return ApiRestResponse.error(MallExceptionEnum.PARA_NOT_COMPLETE);
        }

        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        if (userService.checkAdminRole(user)) {
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }
}
