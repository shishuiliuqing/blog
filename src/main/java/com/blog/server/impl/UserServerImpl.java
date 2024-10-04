package com.blog.server.impl;

import com.blog.mapper.UserMapper;
import com.blog.pojo.BaseUserInfo;
import com.blog.pojo.User;
import com.blog.server.UserServer;
import com.blog.utils.ImageUtil;
import com.blog.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String eMail, String password) {
        Integer result = userMapper.getIDByEMailAndPassword(eMail, password);
        String username = userMapper.getUsernameById(result);
        if (result != null) {
            return JWTUtil.generateJWT(result, username);
        } else return null;
    }

    @Transactional
    @Override
    public void register(String username, String password, String eMail) throws IOException {
        userMapper.insertUserLogin(username, password, eMail);
        Integer id = userMapper.getIdByUsername(username);
        String url = ImageUtil.URL + "/default.png";
        User user = new User(id, username, 0, 0, url, null);
        userMapper.insertUser(user);
    }

    @Override
    public User getUser() {
        Integer id = BaseUserInfo.getId();
        User user = userMapper.getUserById(id);
        user.setLv();
        return user;
    }
}
