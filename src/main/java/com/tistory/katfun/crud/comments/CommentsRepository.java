package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByPostId(Long postId);
}
