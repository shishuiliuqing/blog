package com.blog.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImageUtil {
    public static  File FILE = new File("src\\main\\resources\\static\\images");
    public static String URL = "http://localhost:8080/images";

    public String upload(MultipartFile image) throws IOException {
        //获取图片存放位置路径
        String realPath = FILE.getCanonicalPath();
        //获取图片文件名字
        String originalFilename = image.getOriginalFilename();
        //生成图片文件名字
        String imageName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //存储图片至本地
        image.transferTo(new File(realPath + "\\" + imageName));
        return URL + "/" + imageName;
    }
}
