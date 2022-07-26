package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.comments.dto.CommentsResponseDto;
import com.tistory.katfun.crud.comments.dto.CommentsSaveRequestDto;
import com.tistory.katfun.crud.comments.dto.CommentsUpdateRequestDto;
import com.tistory.katfun.crud.domain.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService{

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<Comments> selectCommentList(Long postId) {
        return commentsRepository.findByPostId(postId);
    }

    @Override
    public CommentsResponseDto viewComment(Long commentId) {
        Comments entity = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. commentId = " + commentId
                ));

        return new CommentsResponseDto(entity);
    }

    @Transactional
    @Override
    public Long saveComment(CommentsSaveRequestDto requestDto) {
        return commentsRepository.save(requestDto.toEntity()).getCommentId();
    }

    @Transactional
    @Override
    public Long updateComment(CommentsUpdateRequestDto requestDto, Long commentId) {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. commentId = " + commentId
                ));

        comments.update(requestDto.getContent());
        return commentId;
    }

    @Transactional
    @Override
    public Long deleteComment(Long commentId) {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. commentId = " + commentId
                ));
        commentsRepository.delete(comments);

        return commentId;
    }

}
