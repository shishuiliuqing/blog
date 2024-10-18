package com.blog.server.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.pojo.Synopsis;
import com.blog.pojo.BaseUserInfo;
import com.blog.server.ArticleServer;
import com.blog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
     * @param content
     */
    @Transactional
    @Override
    public void addArticle(MultipartFile cover, String title, String introduce, String content) {
        try {
            String owner = BaseUserInfo.getUsername();
            articleMapper.addSynopsis(imageUtil.upload(cover), title, introduce, owner);
            Integer id = articleMapper.getNewId(owner);
            articleMapper.addContent(id, content);
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
    @Transactional
    @Override
    public Article getById(Integer id) {
        Synopsis synopsis = articleMapper.getById(id);
        String content = articleMapper.getContentById(id);
        Article article = new Article(synopsis, content);
        return article;
    }

    /**
     * 获取所有文章
     *
     * @return
     */
    @Override
    public List<Synopsis> getAll() {
        return articleMapper.getAll();
    }

    /**
     * 根据用户名获取文章
     *
     * @param username
     * @return
     */
    @Override
    public List<Synopsis> getByUsername(String username) {
        return articleMapper.getArticleByOwner(username);
    }
}
