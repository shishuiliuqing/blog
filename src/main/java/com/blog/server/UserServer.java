package com.blog.server;

import com.blog.pojo.User;

import java.io.IOException;

public interface UserServer {

    /**
     * 登录操作
     * @param eMail
     * @param password
     * @return
     */
    String login(String eMail, String password) ;

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param eMail
     */
    void register(String username, String password,String eMail) throws IOException;

    /**
     * 获取当前用户信息
     * @return
     */
    User getUser();
}
