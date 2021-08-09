package com.mumu.mumumall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String MALL_USER = "mall_user";

    public static final String SALT = "dasfewqeioq]dsada[";

    public static final int ROLE_USER = 1;
    public static final int ROLE_ADMIN = 2;

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
