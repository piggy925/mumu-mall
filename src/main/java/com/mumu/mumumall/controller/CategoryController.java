package com.mumu.mumumall.controller;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.model.request.AddCategoryReq;
import com.mumu.mumumall.model.request.UpdateCategoryReq;
import com.mumu.mumumall.service.CategoryService;
import com.mumu.mumumall.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "商品分类管理接口")
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation("后台添加分类")
    @PostMapping("/admin/category/add")
    public ApiRestResponse addCategory(@Valid @RequestBody AddCategoryReq addCategoryReq) {
        categoryService.add(addCategoryReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台更新分类")
    @PostMapping("/admin/category/update")
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        categoryService.update(updateCategoryReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台删除分类")
    @PostMapping("/admin/category/delete")
    public ApiRestResponse deleteCategory(Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台分类列表")
    @GetMapping("/admin/category/list")
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台分类列表")
    @GetMapping("/category/list")
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryVOList = categoryService.listForCustomer(0);
        return ApiRestResponse.success(categoryVOList);
    }
}