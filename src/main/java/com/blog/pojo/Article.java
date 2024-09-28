package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private String cover;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private String content;
    private String pubDate;
    private String updDate;
    private String owner;
}
