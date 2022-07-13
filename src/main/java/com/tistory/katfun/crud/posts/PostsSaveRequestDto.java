package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import lombok.Builder;

import java.util.Date;

public class PostsSaveRequestDto {

    private String category;
    private String title;
    private String createId;
    private Date createTime;
    private Date lastEditTime;
    private String content;
    private int viewCount;

    @Builder
    public PostsSaveRequestDto(String category, String title, String createId, String content) {
        this.category = category;
        this.title = title;
        this.createId = createId;
        this.createTime = new Date(System.currentTimeMillis());
        this.lastEditTime = new Date(System.currentTimeMillis());
        this.content = content;
        this.viewCount = 0;
    }

    public Posts toEntity() {
        return Posts.builder()
                .category(category)
                .title(title)
                .createId(createId)
                .content(content)
                .build();
    }
}
