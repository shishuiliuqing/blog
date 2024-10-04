package com.blog.server.impl;

import com.blog.mapper.LikeMapper;
import com.blog.pojo.BaseUserInfo;
import com.blog.server.LikeSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServerImpl implements LikeSever {

    @Autowired
    private LikeMapper likeMapper;

    @Transactional
    @Override
    public void addLike(Integer aid) {
        Integer uid = BaseUserInfo.getId();
        likeMapper.addUserLike(uid, aid);
        likeMapper.addArticleLike(aid);
    }

    @Transactional
    @Override
    public void subLike(Integer aid) {
        Integer uid = BaseUserInfo.getId();
        likeMapper.subUserLike(uid, aid);
        likeMapper.subArticleLike(aid);
    }

    @Override
    public boolean getStatus(Integer aid) {
        Integer uid = BaseUserInfo.getId();
        Integer likeId = likeMapper.getUserLike(uid, aid);
        return likeId != null;
    }

}
