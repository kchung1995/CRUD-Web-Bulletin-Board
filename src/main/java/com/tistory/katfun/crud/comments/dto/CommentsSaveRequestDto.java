package com.tistory.katfun.crud.comments.dto;

import com.tistory.katfun.crud.domain.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {

    private Long commentId;
    private Long postId;
    private String createId;
    private String content;
    private Date lastEditTime;
    private Boolean secretYn;
    private Boolean editedYn;

    @Builder
    public CommentsSaveRequestDto( Long postId, String createId, String content, Date lastEditTime, Boolean secretYn, Boolean editedYn) {
        this.postId = postId;
        this.createId = createId;
        this.content = content;
        this.lastEditTime = lastEditTime;
        this.secretYn = secretYn;
        this.editedYn = editedYn;
    }

    public Comments toEntity() {
        return Comments.builder()
                .postId(postId)
                .createId(createId)
                .content(content)
                .lastEditTime(lastEditTime)
                .secretYn(secretYn)
                .editedYn(editedYn)
                .build();
        }
    }
}
