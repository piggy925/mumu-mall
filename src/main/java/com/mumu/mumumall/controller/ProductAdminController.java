package com.mumu.mumumall.controller;

import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.request.AddProductReq;
import com.mumu.mumumall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 后台商品管理Controller.
 */
@RestController
public class ProductAdminController {
    @Resource
    ProductService productService;

    @ApiOperation("后台新增商品")
    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.addProduct(addProductReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台文件上传")
    @PostMapping("/admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String oldFileName = file.getOriginalFilename();
        String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + suffix;
        File fileDir = new File(Constant.FILE_UPLOAD_DIR);
        File newFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDir.exists()) {
            if (!fileDir.mkdir()) {
                return ApiRestResponse.error(MallExceptionEnum.MKDIR_FAIL);
            }
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse.success(getHost(new URI(request.getRequestURL().toString())) + "/images/" + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(MallExceptionEnum.UPLOAD_FILE_FAIL);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveUri;
        try {
            effectiveUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveUri = null;
        }
        return effectiveUri;
    }
}
