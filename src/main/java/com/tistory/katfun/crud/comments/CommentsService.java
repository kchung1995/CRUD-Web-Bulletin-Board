package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.comments.dto.CommentsResponseDto;
import com.tistory.katfun.crud.comments.dto.CommentsSaveRequestDto;
import com.tistory.katfun.crud.comments.dto.CommentsUpdateRequestDto;
import com.tistory.katfun.crud.domain.Comments;

import java.util.HashMap;
import java.util.List;

public interface CommentsService {

    public List<Comments> selectCommentList(Long postId);

    public CommentsResponseDto viewComment(Long commentId);

    public Long saveComment(CommentsSaveRequestDto requestDto);

    public Long updateComment(CommentsUpdateRequestDto requestDto, Long commentId);

    public Long deleteComment(Long commentId);

}
