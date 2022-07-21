package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import com.tistory.katfun.crud.posts.dto.PostsResponseDto;
import com.tistory.katfun.crud.posts.dto.PostsSaveRequestDto;
import com.tistory.katfun.crud.posts.dto.PostsUpdateRequestDto;

import java.util.List;

public interface PostsService {

    public List<Posts> selectPostsList();

    public PostsResponseDto viewPost (Long postId);

    public Long savePost(PostsSaveRequestDto requestDto);

    public Long updatePost(Long postId, PostsUpdateRequestDto requestDto);

    public Long deletePost(Long postId);
}
