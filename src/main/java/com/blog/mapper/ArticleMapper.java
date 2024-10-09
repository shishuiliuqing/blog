package com.blog.mapper;

import com.blog.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 添加文章
     *
     * @param cover
     * @param title
     * @param introduce
     * @param owner
     */
    @Insert("insert into article (cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date,owner) VALUES " +
            "(#{cover},#{title},0,0,0,#{introduce},now(),now(),#{owner})")
    void addArticle(String cover, String title, String introduce, String owner);


    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date ,owner from blog.article where id = #{id}")
    Article getById(Integer id);


    /**
     * 获取所有文章
     *
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date , owner from blog.article")
    List<Article> getAll();

    /**
     * 根据用户名获取文章
     * @param owner
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date, owner from article where owner = #{owner}")
    List<Article> getArticleByOwner(String owner);
}
