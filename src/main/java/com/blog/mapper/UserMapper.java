package com.blog.mapper;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Select("SELECT id, username, experience, profile_picture, signature from user")
    User getByUsername(String username);

    /**
     * 修改用户头像url
     *
     * @param profilePicture
     */
    @Update("update user set profile_picture = #{profilePicture} where id = #{id}")
    void updateProfilePicture(Integer id, String profilePicture);

    /**
     * 获取用户头像
     *
     * @param id
     * @return
     */
    @Select("select profile_picture from user where id = #{id}")
    String getProfilePicture(Integer id);
}
