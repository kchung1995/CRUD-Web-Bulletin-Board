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

    @Override
    @Transactional
    public Long savePost(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
