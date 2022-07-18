package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<Posts> selectPostsList() {
        return postsRepository.findAll();
    }

    @Transactional
    @Override
    public Long savePost(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getPostId();
    }

    @Transactional
    @Override
    public Long update(Long postId, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 게시물이 존재하지 않습니다. postId = " + postId
                ));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return postId;
    }

    @Override
    public PostsResponseDto findById (Long postId) {
        Posts entity = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 게시물이 존재하지 않습니다. postId = " + postId
                ));

        return new PostsResponseDto(entity);
    }
}
