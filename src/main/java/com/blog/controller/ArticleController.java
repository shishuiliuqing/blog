package com.blog.controller;

import com.blog.pojo.Article;
import com.blog.pojo.Draft;
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


//    /**
//     * 添加文章
//     *
//     * @param cover
//     * @param title
//     * @param introduce
//     * @return
//     */
//    @PostMapping
//    public Result addArticle(MultipartFile cover, String title, String introduce,String content) {
//        articleServer.addArticle(cover, title, introduce,content);
//        return Result.success("发布成功",null);
//    }


    /**
     * 新建草稿
     *
     * @return
     */
    @PostMapping("/createDraft")
    public Result createDraft() {
        log.info("新建草稿");
        Integer draftId = articleServer.createDraft();
        return Result.success("新建成功", draftId);
    }

    /**
     * 获取当前用户所有草稿
     *
     * @return
     */
    @GetMapping("/getDrafts")
    public Result getDrafts() {
        log.info("获取当前用户所有草稿");
        List<Draft> drafts = articleServer.getDrafts();
        return Result.success(drafts);
    }

    /**
     * 修改文章草稿
     *
     * @return
     */
    @PostMapping("/update")
    public Result update(Integer id, String cover, String title, String introduce, String content) {
        log.info("cover:{}\ntitle:{}\nintroduce{}\ncontent{}", cover, title, introduce, content);
        articleServer.update(id, cover, title, introduce, content);
        return Result.success("修改保存成功", null);
    }

    @PostMapping("/release/{id}")
    public Result release(@PathVariable Integer id){
        log.info("发布文章ID：{}",id);
        articleServer.release(id);
        return Result.success("文章发布成功",null);
    };

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
