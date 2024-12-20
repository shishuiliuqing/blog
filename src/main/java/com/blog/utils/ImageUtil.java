package com.blog.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Component
public class ImageUtil {
    //图片存储地址
    public static File IMAGES = new File("src\\main\\resources\\static\\images");
    //回收站存储地址
    public static File RECYCLE_BIN = new File("src\\main\\resources\\static\\recycle_bin");
    //url映射地址
    public static String URL = "http://localhost:8080/images";
    //默认用户头像
    public static String DEFAULT = "default.png";

    /**
     * 将文件存储在本地后生成URL
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile image) throws IOException {
        //获取图片存放位置路径
        String realPath = IMAGES.getCanonicalPath();
        //获取图片文件名字
        String originalFilename = image.getOriginalFilename();
        //生成图片文件名字
        String imageName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //存储图片至本地
        image.transferTo(new File(realPath + "\\" + imageName));
        return URL + "/" + imageName;
    }

    /**
     * 根据URL删除本地文件,回收站备份存储
     *
     * @param imageURL
     * @return
     */
    public static void delete(String imageURL) throws IOException {
        File[] files = getFile(imageURL);
        //图片地址
        File image = files[0];
        //回收站地址
        File bin = files[1];

        if (image.exists()) {
            if (!bin.exists()) {
                //文件拷贝用于回滚
                Files.copy(image.toPath(), bin.toPath());
            }
            //图片删除
            Files.delete(image.toPath());
        }
    }

    /**
     * 图片文件回滚处理
     *
     * @param imageURL
     */
    public static void rollBack(String imageURL) throws IOException {
        File[] files = getFile(imageURL);
        //图片地址
        File image = files[0];
        //回收站地址
        File bin = files[1];
        //图片未进站
        if(!bin.exists()) return;
        if (!image.exists()) {
            //图片回滚
            Files.copy(bin.toPath(), image.toPath());
        }
    }

    /**
     * 清空回收站
     * @throws IOException
     */
    public static void clearBin() throws IOException {
        //回收站路径
        String binPath = RECYCLE_BIN.getCanonicalPath();
        File bin = new File(binPath);

        File[] files = bin.listFiles();
        if (files != null) {
            for (File file : files) {
                if(file.delete()) {
                    log.info("{}删除成功！",file.getName());
                }
            }
        }
    }

    /**
     * 获取文件原地址与回收站地址
     *
     * @param imageURL
     * @return
     */
    public static File[] getFile(String imageURL) throws IOException {
        //获取图片存放位置路径
        String imageName = imageURL.substring(URL.length() + 1);
        File imageFile = new File(IMAGES, imageName);
        String realPath = imageFile.getCanonicalPath();
        File image = new File(realPath);
        //回收站路径
        File binFile = new File(RECYCLE_BIN, imageName);
        String binPath = binFile.getCanonicalPath();
        File bin = new File(binPath);

        return new File[]{image, bin};
    }
}
