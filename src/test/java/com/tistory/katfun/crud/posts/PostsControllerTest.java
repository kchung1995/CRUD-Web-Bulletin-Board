package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Transactional
    @Test
    @DisplayName("게시물이_등록된다")
    public void postsCreate() throws Exception {

        // given
        LocalDate now = LocalDate.now();

        String category = "질문";
        String title = "안녕하세요 JPA에 대해 질문이 있습니다";
        String createId = "KAKAO00001";
        Date createTime = new Date(System.currentTimeMillis());
        Date lastEditTime = new Date(System.currentTimeMillis());
        String content = "안녕하세요, 질문이 있어서 게시물을 작성합니다. \nJPQL은 이렇게 쓰는게 맞나요?";
        int viewCount = 0;

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .category(category)
                .title(title)
                .createId(createId)
                .createTime(createTime)
                .lastEditTime(lastEditTime)
                .content(content)
                .viewCount(viewCount)
                .build();

        String postId = requestDto.getCreateId();

        String url = "http://localhost:" + port + "/posts/" + postId;

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> createdPosts = postsRepository.findAll();
        assertThat(createdPosts.get(0).getCategory()).isEqualTo(category);
        assertThat(createdPosts.get(0).getTitle()).isEqualTo(title);
        assertThat(createdPosts.get(0).getCreateId()).isEqualTo(createId);
//        assertThat(createdPosts.get(0).getCreateTime()).isEqualTo(createTime);
//        assertThat(createdPosts.get(0).getLastEditTime()).isEqualTo(lastEditTime);
        assertThat(createdPosts.get(0).getContent()).isEqualTo(content);
        assertThat(createdPosts.get(0).getViewCount()).isEqualTo(viewCount);
    }

//    @Transactional
    @Test
    @DisplayName("게시물이_수정된다")
    public void postEdit() throws Exception {

        // given
        LocalDate now = LocalDate.now();

        String category = "질문";
        String title = "안녕하세요 JPA에 대해 질문이 있습니다";
        String createId = "KAKAO00001";
        Date createTime = new Date(System.currentTimeMillis());
        Date lastEditTime = new Date(System.currentTimeMillis());
        String content = "안녕하세요, 질문이 있어서 게시물을 작성합니다. \nJPQL은 이렇게 쓰는게 맞나요?";
        int viewCount = 0;

        Posts savedPosts = postsRepository.save(Posts.builder()
                .category(category)
                .title(title)
                .createId(createId)
                .createTime(createTime)
                .lastEditTime(lastEditTime)
                .content(content)
                .viewCount(viewCount)
                .build()
        );

        Long postId = savedPosts.getPostId();
        String title2 = "(완료) 안녕하세요 JPA에 대해 질문이 있습니다";
        String content2 = "괜찮습니다, 질문은 해결했습니다. 감사합니다.";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(title2)
                .content(content2)
                .build();

        String url = "http://localhost:" + port + "/posts/" + postId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> editedPosts = postsRepository.findAll();
        assertThat(editedPosts.get(0).getTitle()).isEqualTo(title2);
        assertThat(editedPosts.get(0).getContent()).isEqualTo(content2);
    }
}
