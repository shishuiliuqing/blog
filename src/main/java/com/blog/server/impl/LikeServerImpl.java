package com.blog.server.impl;

import com.blog.mapper.LikeMapper;
import com.blog.pojo.BaseUserInfo;
import com.blog.server.LikeSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServerImpl implements LikeSever {

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public boolean getStatus(Integer aid) {
        Integer uid = Integer.parseInt(BaseUserInfo.get("id"));
        Integer likeId = likeMapper.getUserLike(uid, aid);
        return likeId != null;
    }
}
