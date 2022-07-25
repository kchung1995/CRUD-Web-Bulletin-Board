package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.domain.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
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

    }

    @Override
    public CommentsResponseDto viewComment(Long postId, Long commentId) {
        Comments entity = commentsRepository.findById(postId, commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. postId = " + postId
                                + ", commentId = " + commentId
                ));

        return new CommentsResponseDto(entity);
    }

    @Transactional
    @Override
    public HashMap<String, Long> saveComment(CommentsSaveRequestDto requestDto) {
        return commentsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    @Override
    public HashMap<String, Long> updateComment(CommentsUpdateRequestDto requestDto, Long postId, Long commentId) {
        Comments comments = commentsRepository.findById(postId, commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. postId = " + postId
                                + ", commentId = " + commentId
                ));

        //comments.update(requestDto.getContent());

        HashMap<String, Long> id = new HashMap<String, Long>();
        id.put("postId", postId);
        id.put("commentId", commentId);

        return id;
    }

    @Transactional
    @Override
    public HashMap<String, Long> deleteComment(Long postId, Long commentId) {
        Comments comments = commentsRepository.findById(postId, commentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 댓글이 존재하기 않습니다. postId = " + postId
                                + ", commentId = " + commentId
                ));
        commentsRepository.delete(comments);

        HashMap<String, Long> id = new HashMap<String, Long>();
        id.put("postId", postId);
        id.put("commentId", commentId);

        return id;
    }

}
