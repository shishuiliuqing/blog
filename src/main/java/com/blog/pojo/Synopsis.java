package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Synopsis {
    private Integer id;
    private String cover;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private String introduce;
    private String pubDate;
    private String updDate;
    private String owner;
}
