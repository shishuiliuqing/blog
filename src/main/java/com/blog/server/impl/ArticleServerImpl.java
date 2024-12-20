package com.blog.server.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.pojo.Draft;
import com.blog.pojo.Synopsis;
import com.blog.pojo.BaseUserInfo;
import com.blog.server.ArticleServer;
import com.blog.utils.ImageUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addArticle(String cover, String title, String introduce, String content) {
        String owner = BaseUserInfo.getUsername();
        if(Strings.isEmpty(title)) title = "未命名";
        if(Strings.isEmpty(introduce)) introduce = "这个用户很懒！没写简介";
        if(Strings.isEmpty(content)) content = "";
        articleMapper.addSynopsis(cover, title, introduce, owner);
        Integer id = articleMapper.getNewId(owner);
        articleMapper.addContent(id, content);
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


//    /**
//     * 新建草稿
//     *
//     * @return
//     */
//    @Transactional
//    @Override
//    public Integer createDraft() {
//        String owner = BaseUserInfo.getUsername();
//        articleMapper.addSynopsis("", "未命名", "", owner);
//        Integer draftId = articleMapper.getNewId(owner);
//        articleMapper.addContent(draftId, "");
//        return draftId;
//    }

    /**
     * 获取当前用户所有草稿
     *
     * @return
     */
    @Override
    public List<Draft> getDrafts() {
        String owner = BaseUserInfo.getUsername();
        List<Draft> drafts = articleMapper.getDrafts(owner);
        return drafts;
    }

    /**
     * 文章更新
     *
     * @param id
     * @param cover
     * @param title
     * @param introduce
     * @param content
     */
    @Transactional
    @Override
    public void update(Integer id, String cover, String title, String introduce, String content) {
        if (title == null) title = "";
        if (cover == null) cover = "";
        if (introduce == null) introduce = "";
        if (content == null) content = "";
        articleMapper.updateSynopsis(id, cover, title, introduce);
        articleMapper.updateContent(id, content);
    }

    @Override
    public void release(Integer id) {
        articleMapper.setStatus(id);
    }
}
