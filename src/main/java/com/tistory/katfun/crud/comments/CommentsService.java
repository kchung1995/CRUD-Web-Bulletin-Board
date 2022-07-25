package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.domain.Comments;

import java.util.HashMap;
import java.util.List;

public interface CommentsService {

    public List<Comments> selectCommentList(Long postId);

    public CommentsResponseDto viewComment(Long postId, Long commentId);

    public HashMap<String, Long> saveComment(CommentsSaveRequestDto requestDto);

    public HashMap<String, Long> updateComment(CommentsUpdateRequestDto requestDto, Long postId, Long commentId);

    public HashMap<String, Long> deleteComment(Long postId, Long commentId);

}
