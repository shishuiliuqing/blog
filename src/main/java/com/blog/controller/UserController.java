package com.blog.controller;

import ch.qos.logback.core.util.StringUtil;
import com.blog.pojo.Result;
import com.blog.pojo.User;
import com.blog.server.UserServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@Slf4j
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServer userServer;

    /**
     * 登录操作
     *
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(String email, String password) {
        log.info("用户名：{} 密码：{}", email, password);
        String token = userServer.login(email, password);
        if (StringUtil.isNullOrEmpty(token)) {
            return Result.fail("登录失败,请检查用户名或密码");
        } else return Result.success("登陆成功", token);
    }


    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(String username, String password, String email) {
        log.info("用户注册--用户名为：{},密码为：{},邮箱为：{}", username, password, email);
        try {
            userServer.register(username, password, email);
            return Result.success("注册成功", null);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException)
                return Result.fail("当前用户名已存在");
            else return Result.error();
        }
    }


    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/getUser")
    public Result getUser() {
        log.info("获取当前用户信息");
        User user = userServer.getUser();
        return Result.success(user);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/getByUsername")
    public Result getByUsername(String username) {
        log.info("用户名为{}",username);
        User user = userServer.getByUsername(username);
        return Result.success(user);
    }
}
