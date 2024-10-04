package com.blog.pojo;

import java.util.HashMap;
import java.util.Map;

//全局信息类
public class BaseUserInfo {

    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(2);
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    //将用户信息添加到线程map中
    public static void put(String key, String value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value);
    }

    //获取线程map中的数据
    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.get(key);
    }

    //根据token获取用户id
    public static Integer getId() {
        return Integer.parseInt(get("id"));
    }

    //根据token获取用户名
    public static String getUsername() {
        return get("username");
    }
}
