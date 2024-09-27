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
     * @param cover
     * @param title
     * @param content
     */
    @Insert("insert into article (cover, title, view_count, like_count, comment_count, content, pub_date, upd_date) VALUES " +
            "(#{cover},#{title},0,0,0,#{content},now(),now())")
    void addArticle(String cover, String title, String content);


    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, content, pub_date, upd_date from blog.article where id = #{id}")
    Article getById(Integer id);


    /**
     * 获取所有文章
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, content, pub_date, upd_date from blog.article")
    List<Article> getAll();
}
