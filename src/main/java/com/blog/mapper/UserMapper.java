package com.blog.mapper;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户id
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select id from user_login where username = #{username} and password = #{password}")
    Integer getIDByUsernameAndPassword(String username, String password);

    /**
     * 添加用户名和密码
     *
     * @param username
     * @param password
     */
    @Insert("insert into user_login (username, password) value (#{username},#{password})")
    void insertUserLogin(String username, String password);

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Select("select id, username, experience, profile_picture, signature from user where id = #{id}")
    User getUserById(Integer id);

    /**
     * @param user
     */
    @Insert("insert into user (id, username, experience, profile_picture, signature) value (#{id},#{username},#{experience},#{profilePicture},#{signature})")
    void insertUser(User user);
}
