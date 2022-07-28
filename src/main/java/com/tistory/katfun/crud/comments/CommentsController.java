package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.comments.dto.CommentsResponseDto;
import com.tistory.katfun.crud.comments.dto.CommentsSaveRequestDto;
import com.tistory.katfun.crud.comments.dto.CommentsUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    // 지정한 게시물에 대한 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public void selectCommentsList(@PathVariable Long postId) {

    }

    // 댓글 내용 조회
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentsResponseDto view(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentsService.viewComment(commentId);
    }

    // 댓글 추가
    @PostMapping("/posts/{postId}/comments/{commentId}")
    public Long addComment(
            @RequestBody CommentsSaveRequestDto requestDto,
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        return commentsService.saveComment(requestDto);
    }

    // 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public Long editComment(
            @RequestBody CommentsUpdateRequestDto requestDto,
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        return commentsService.updateComment(requestDto, commentId);
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentsService.deleteComment(commentId);
    }
}
