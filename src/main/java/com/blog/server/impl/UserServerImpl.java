package com.blog.server.impl;

import com.blog.mapper.UserMapper;
import com.blog.pojo.BaseUserInfo;
import com.blog.pojo.User;
import com.blog.server.UserServer;
import com.blog.utils.ImageUtil;
import com.blog.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
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
        user.setSignature();
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = userMapper.getByUsername(username);
        user.setLv();
        user.setSignature();
        return user;
    }

    @Transactional
    @Override
    public void updateProfilePicture(String profilePicture) {
        Integer id = BaseUserInfo.getId();
        //获取原头像
        String imageURL = userMapper.getProfilePicture(id);
        try {
            //替换新头像
            userMapper.updateProfilePicture(id, profilePicture);
            //删除原来头像
            if(!imageURL.contains(ImageUtil.DEFAULT)) {
                //删除图片
                ImageUtil.delete(imageURL);
            }
        } catch (Exception e) {
            try {
                //事务回滚
                ImageUtil.rollBack(imageURL);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                ImageUtil.clearBin();
            } catch (IOException e) {
                log.info("回收站删除出错！请检查回收站情况！");
            }
        }
    }
}
