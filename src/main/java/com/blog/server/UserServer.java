package com.blog.server;

import com.blog.pojo.User;
import org.springframework.stereotype.Service;

public interface UserServer {

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password) ;

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 获取当前用户信息
     * @return
     */
    User getUser();
}
