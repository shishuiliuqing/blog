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
     * @param eMail
     * @param password
     * @return
     */
    @Select("select id from user_login where e_mail = #{eMail} and password = #{password}")
    Integer getIDByEMailAndPassword(String eMail, String password);

    /**
     * 添加用户名和密码
     *
     * @param username
     * @param password
     * @param eMail
     */
    @Insert("insert into user_login (username, password,e_mail) value (#{username},#{password},#{eMail})")
    void insertUserLogin(String username, String password, String eMail);

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

    /**
     * 根据用户名寻找用户id
     *
     * @param username
     * @return
     */
    @Select("select id from user_login where username = #{username}")
    Integer getIdByUsername(String username);

    /**
     * 根据id寻找用户名
     *
     * @param result
     * @return
     */
    @Select("select username from user_login where id = #{result}")
    String getUsernameById(Integer result);
}
