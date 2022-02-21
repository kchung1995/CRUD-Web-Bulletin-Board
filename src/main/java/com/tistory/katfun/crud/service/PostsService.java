package com.tistory.katfun.crud.service;

import com.tistory.katfun.crud.domain.posts.Posts;
import com.tistory.katfun.crud.domain.posts.PostsRepository;
import com.tistory.katfun.crud.web.dto.PostsResponseDto;
import com.tistory.katfun.crud.web.dto.PostsSaveRequestDto;
import com.tistory.katfun.crud.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    public final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.\n게시글 ID: " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.\n게시글 ID: " + id));
        return new PostsResponseDto(entity);
    }
}
