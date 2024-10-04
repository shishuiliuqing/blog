package com.blog.controller;

import com.blog.pojo.Result;
import com.blog.server.LikeSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeSever likeSever;

    /**
     * 获取当前用户对某一文章的点赞状态
     *
     * @param aid
     * @return
     */
    @GetMapping("/{aid}")
    public Result likeStatus(@PathVariable Integer aid) {
        boolean flag = likeSever.getStatus(aid);
        if (flag) return Result.success("LIKED", null);
        else return Result.success("NOT_LIKE", null);
    }

    /**
     * 根据文章id进行点赞或取消点赞
     * @param aid
     * @return
     */
    @PostMapping
    public Result likeByAid(Integer aid) {
        if (likeSever.getStatus(aid)) {
            likeSever.subLike(aid);
            return Result.success("已取消点赞",null);
        }
        else {
            likeSever.addLike(aid);
            return Result.success("点赞成功", null);
        }
    }
}
