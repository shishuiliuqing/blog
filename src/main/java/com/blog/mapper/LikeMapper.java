package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
