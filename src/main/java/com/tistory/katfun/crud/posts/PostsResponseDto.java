package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import lombok.Getter;

import java.util.Date;

@Getter
public class PostsResponseDto {

    private Long postId;
    private String category;
    private String title;
    private String createId;
    private Date createTime;
    private Date lastEditTime;
    private String content;
    private int viewCount;

    public PostsResponseDto(Posts entity) {
        this.postId = entity.getPostId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.createId = entity.getCreateId();
        this.createTime = entity.getCreateTime();
        this.lastEditTime = entity.getLastEditTime();
        this.content = entity.getContent();
        this.viewCount = entity.getViewCount();
    }
}
