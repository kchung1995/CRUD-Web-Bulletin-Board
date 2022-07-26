package com.tistory.katfun.crud.comments.dto;

import com.tistory.katfun.crud.domain.Comments;
import lombok.Getter;

import java.util.Date;

@Getter
public class CommentsResponseDto {

    private Long commentId;
    private Long postId;
    private String createId;
    private String content;
    private Date lastEditTime;
    private Boolean secretYn;
    private Boolean editedYn;

    public CommentsResponseDto(Comments entity) {
        this.commentId = entity.getCommentId();
        this.postId = entity.getPostId();
        this.createId = entity.getCreateId();
        this.content = entity.getContent();
        this.lastEditTime = entity.getLastEditTime();
        this.secretYn = entity.getSecretYn();
        this.editedYn = entity.getEditedYn();
    }
}


