package com.blog.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeMapper {

    /**
     * 查找user_like是否有对应用户id和文章id信息
     *
     * @param uid
     * @param aid
     * @return
     */
    @Select("select id from user_like where uid = #{uid} and aid = #{aid} ")
    Integer getUserLike(Integer uid, Integer aid);


    /**
     * 添加用户点赞信息
     *
     * @param uid
     * @param aid
     */
    @Insert("insert into user_like (uid, aid) VALUE (#{uid},#{aid})")
    void addUserLike(Integer uid, Integer aid);


    /**
     * 文章点赞数+1
     *
     * @param aid
     */
    @Update("update article set like_count = like_count + 1 where id = #{aid}")
    void addArticleLike(Integer aid);


    /**
     * 删除用户点赞信息
     *
     * @param uid
     * @param aid
     */
    @Delete("delete from user_like where uid = #{uid} and aid = #{aid}")
    void subUserLike(Integer uid, Integer aid);

    /**
     * 文章点赞数-1
     *
     * @param aid
     */
    @Update("update article set like_count = like_count-1 where id = #{aid}")
    void subArticleLike(Integer aid);
}
