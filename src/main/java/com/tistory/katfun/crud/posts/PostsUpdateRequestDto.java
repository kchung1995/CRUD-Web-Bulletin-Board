package com.tistory.katfun.crud.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;
    private Date lastEditDate;
    private int viewCount;

    @Builder
    public PostsUpdateRequestDto(String title, String content, int viewCount) {
        this.title = title;
        this.content = content;
        this.lastEditDate = new Date(System.currentTimeMillis());
        this.viewCount = viewCount + 1;
    }
}
