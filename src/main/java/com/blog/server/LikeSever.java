package com.blog.server;

public interface LikeSever {

    /**
     * 获取用户点赞状态
     * @param aid
     * @return
     */
    boolean getStatus(Integer aid) ;
}
