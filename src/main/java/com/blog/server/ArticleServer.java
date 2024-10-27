package com.blog.server;

import com.blog.pojo.Article;
import com.blog.pojo.Draft;
import com.blog.pojo.Synopsis;

import java.util.List;

public interface ArticleServer {
//    /**
//     * 添加文章
//     * @param cover
//     * @param title
//     * @param introduce
//     */
//    void addArticle(MultipartFile cover, String title, String introduce,String content);


    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    Article getById(Integer id);


    /**
     * 获取全部文章
     *
     * @return
     */
    List<Synopsis> getAll();

    /**
     * 根据用户名获取文章
     *
     * @param username
     * @return
     */
    List<Synopsis> getByUsername(String username);

    /**
     * 新建草稿
     *
     * @return
     */
    Integer createDraft();

    /**
     * 获取当前用户所有草稿
     *
     * @return
     */
    List<Draft> getDrafts();

    /**
     * 修改草稿
     *
     * @param cover
     * @param title
     * @param introduce
     * @param content
     */
    void update(Integer id, String cover, String title, String introduce, String content);

    /**
     * 文章发布
     *
     * @param id
     */
    void release(Integer id);
}
