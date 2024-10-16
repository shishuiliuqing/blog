package com.blog.controller;

import com.blog.pojo.Article;
import com.blog.pojo.Synopsis;
import com.blog.pojo.Result;
import com.blog.server.ArticleServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    @Autowired
    private ArticleServer articleServer;

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @GetMapping("/getByAid/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("文章ID：{}", id);
        Article article = articleServer.getById(id);
        return Result.success(article);
    }


    /**
     * 获取所有文章
     *
     * @return
     */
    @GetMapping("/getAll")
    public Result getAll() {
        log.info("获取全部文章");
        List<Synopsis> synopses = articleServer.getAll();
        return Result.success(synopses);
    }


    /**
     * 添加文章
     *
     * @param cover
     * @param title
     * @param introduce
     * @return
     */
    @PostMapping
    public Result addArticle(MultipartFile cover, String title, String introduce,String content) {
        articleServer.addArticle(cover, title, introduce,content);
        return Result.success("发布成功",null);
    }

    /**
     * 根据用户名获取文章
     *
     * @param username
     * @return
     */
    @GetMapping("/getByUsername")
    public Result getByUsername(String username) {
        List<Synopsis> synopses = articleServer.getByUsername(username);
        return Result.success(synopses);
    }
}
