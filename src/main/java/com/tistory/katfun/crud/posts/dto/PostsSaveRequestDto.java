package com.tistory.katfun.crud.posts.dto;

import com.tistory.katfun.crud.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String category;
    private String title;
    private String createId;
    private Date createTime;
    private Date lastEditTime;
    private String content;
    private int viewCount;

    @Builder
    public PostsSaveRequestDto(String category, String title, String createId, Date createTime, Date lastEditTime, String content, int viewCount) {
        this.category = category;
        this.title = title;
        this.createId = createId;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.content = content;
        this.viewCount = viewCount;
    }

    public Posts toEntity() {
        return Posts.builder()
                .category(category)
                .title(title)
                .createId(createId)
                .createTime(createTime)
                .lastEditTime(lastEditTime)
                .content(content)
                .viewCount(viewCount)
                .build();
    }

}
