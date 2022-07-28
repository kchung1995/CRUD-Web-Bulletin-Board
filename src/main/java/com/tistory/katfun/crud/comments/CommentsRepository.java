package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @OrderBy("commentId asc")
    List<Comments> findByPostId(Long postId);
}
