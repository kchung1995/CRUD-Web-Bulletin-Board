package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostsService {
    public List<Posts> selectPostsList();

    public Long savePost(PostsSaveRequestDto requestDto);
}
