package com.blog.server;

public interface LikeSever {

    /**
     * 根据文章id进行点赞
     * @param aid
     */
    void addLike(Integer aid);

    /**
     * 获取用户点赞状态
     *
     * @param aid
     * @return
     */
    boolean getStatus(Integer aid);

    /**
     * 根据文章id取消点赞
     * @param aid
     */
    void subLike(Integer aid);
}
