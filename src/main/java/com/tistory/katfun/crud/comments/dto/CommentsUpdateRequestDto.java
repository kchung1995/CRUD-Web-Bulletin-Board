package com.tistory.katfun.crud.comments.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {

    private String content;
    private Date lastEditTime;
    private Boolean editedYn;

    @Builder
    public CommentsUpdateRequestDto(String content, Boolean editedYn) {
        this.content = content;
        this.lastEditTime = new Date(System.currentTimeMillis());
        this.editedYn = editedYn;
    }
}
