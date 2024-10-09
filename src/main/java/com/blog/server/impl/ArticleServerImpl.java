package com.blog.server.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.pojo.BaseUserInfo;
import com.blog.server.ArticleServer;
import com.blog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArticleServerImpl implements ArticleServer {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ImageUtil imageUtil;

    /**
     * 添加文章
     *
     * @param cover
     * @param title
     * @param introduce
     */
    @Override
    public void addArticle(MultipartFile cover, String title, String introduce) {
        try {
            String owner = BaseUserInfo.getUsername();
            articleMapper.addArticle(imageUtil.upload(cover), title, introduce, owner);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @Override
    public Article getById(Integer id) {
        Article article = articleMapper.getById(id);
        return article;
    }

    /**
     * 获取所有文章
     *
     * @return
     */
    @Override
    public List<Article> getAll() {
        return articleMapper.getAll();
    }

    /**
     * 根据用户名获取文章
     *
     * @param username
     * @return
     */
    @Override
    public List<Article> getByUsername(String username) {
        return articleMapper.getArticleByOwner(username);
    }
}
