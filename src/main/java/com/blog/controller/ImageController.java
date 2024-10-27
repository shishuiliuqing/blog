package com.blog.controller;

import com.blog.pojo.Result;
import com.blog.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/imageFiles")
@CrossOrigin(origins = "*")
public class ImageController {
    /**
     * 文件上传
     *
     * @param image
     * @return
     */
    @PostMapping
    public Result upload(@RequestBody MultipartFile image) {
        log.info("正在上传文件。。。");
        try {
            String URL = ImageUtil.upload(image);
            return Result.success("图片上传成功", URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
