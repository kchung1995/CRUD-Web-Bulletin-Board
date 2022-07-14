package com.tistory.katfun.crud.posts;

import com.tistory.katfun.crud.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        String url = "http://localhost:" + port + "/posts/addPost";

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

}
