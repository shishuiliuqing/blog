package com.blog.server.impl;

import com.blog.mapper.UserMapper;
import com.blog.pojo.BaseUserInfo;
import com.blog.pojo.User;
import com.blog.server.UserServer;
import com.blog.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        Integer result = userMapper.getIDByUsernameAndPassword(username, password);
        if (result != null) {
            return JWTUtil.generateJWT(result, username);
        } else return null;
    }

    @Transactional
    @Override
    public void register(String username, String password) {
        userMapper.insertUserLogin(username, password);
    }

    @Override
    public User getUser() {
        Integer id = Integer.parseInt(BaseUserInfo.get("id"));
        User user = userMapper.getUserById(id);
        user.setLv();
        return user;
    }
}
