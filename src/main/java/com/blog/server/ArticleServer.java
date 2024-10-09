package com.blog.server;

import com.blog.pojo.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleServer {
    /**
     * 添加文章
     * @param cover
     * @param title
     * @param introduce
     */
    void addArticle(MultipartFile cover, String title, String introduce);


    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    Article getById(Integer id);


    /**
     * 获取全部文章
     * @return
     */
    List<Article> getAll();

    /**
     * 根据用户名获取文章
     * @param username
     * @return
     */
    List<Article> getByUsername(String username);
}
