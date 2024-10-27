package com.blog.mapper;

import com.blog.pojo.Draft;
import com.blog.pojo.Synopsis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 添加封面简介
     *
     * @param cover
     * @param title
     * @param introduce
     * @param owner
     */
    @Insert("insert into synopsis (cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date,owner) VALUES " +
            "(#{cover},#{title},0,0,0,#{introduce},now(),now(),#{owner})")
    void addSynopsis(String cover, String title, String introduce, String owner);

    /**
     * 获取当前用户新发表的文章id
     *
     * @param owner
     * @return
     */
    @Select("select max(id) from synopsis where owner = #{owner}")
    Integer getNewId(String owner);

    /**
     * 添加文章正文
     *
     * @param id
     * @param content
     */
    @Insert("insert into article_content (id, content) VALUE (#{id},#{content})")
    void addContent(Integer id, String content);

    /**
     * 根据id获取文章简介封面
     *
     * @param id
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date ,owner from blog.synopsis where id = #{id} and status = 1")
    Synopsis getById(Integer id);


    /**
     * 获取所有文章简介封面
     *
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date , owner from blog.synopsis where status = 1")
    List<Synopsis> getAll();

    /**
     * 根据用户名获取文章
     *
     * @param owner
     * @return
     */
    @Select("select id, cover, title, view_count, like_count, comment_count, introduce, pub_date, upd_date, owner from synopsis where owner = #{owner}")
    List<Synopsis> getArticleByOwner(String owner);

    /**
     * 根据id获取正文
     *
     * @param id
     * @return
     */
    @Select("select content from article_content where id = #{id}")
    String getContentById(Integer id);

    /**
     * 获取用户未发布的所有文章草稿
     *
     * @param owner
     * @return
     */
    @Select("select id, title, pub_date, upd_date from synopsis where owner = #{owner} and status = 0")
    List<Draft> getDrafts(String owner);

    /**
     * 修改文章简介封面
     *
     * @param id
     * @param cover
     * @param title
     * @param introduce
     */
    @Update("update synopsis set cover = #{cover},title = #{title},introduce = #{introduce},upd_date = now() where id = #{id}")
    void updateSynopsis(Integer id, String cover, String title, String introduce);

    /**
     * 修改正文
     * @param id
     * @param content
     */
    @Update("update article_content set content = #{content} where id = #{id}")
    void updateContent(Integer id,String content);
}
