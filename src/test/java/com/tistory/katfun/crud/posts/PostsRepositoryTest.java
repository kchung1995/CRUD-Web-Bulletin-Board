package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("기본_게시글이_저장되고_불러와진다")
    public void saveAndSelect() {

        //given
        LocalDate now = LocalDate.now();

        String category = "질문";
        String title = "안녕하세요 JPA에 대해 질문이 있습니다";
        String createId = "KAKAO00001";
        Date createTime = new Date(System.currentTimeMillis());
        Date lastEditTime = new Date(System.currentTimeMillis());
        String content = "안녕하세요, 질문이 있어서 게시물을 작성합니다. \nJPQL은 이렇게 쓰는게 맞나요?";
        int viewCount = 0;

        postsRepository.save(
                Posts.builder()
                        .category(category)
                        .title(title)
                        .createId(createId)
                        .createTime(createTime)
                        .lastEditTime(lastEditTime)
                        .content(content)
                        .viewCount(viewCount)
                        .build()
        );

        //when
        List<Posts> postsSelected = postsRepository.findAll();

        //then
        Posts savedPost = postsSelected.get(0);
        assertThat(savedPost.getCategory()).isEqualTo(category);
        assertThat(savedPost.getCreateId()).isEqualTo(createId);
//        assertThat(savedPost.getCreateTime()).isEqualTo(createTime);
//        assertThat(savedPost.getLastEditTime()).isEqualTo(lastEditTime);
        assertThat(savedPost.getContent()).isEqualTo(content);
        assertThat(savedPost.getViewCount()).isEqualTo(viewCount);
    }
}