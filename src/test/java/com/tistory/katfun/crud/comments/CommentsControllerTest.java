package com.tistory.katfun.crud.comments;

import com.tistory.katfun.crud.domain.Comments;
import com.tistory.katfun.crud.domain.Posts;
import com.tistory.katfun.crud.posts.PostsRepository;
import com.tistory.katfun.crud.posts.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    @DisplayName("게시글에_댓글이_등록된다")
    public void saveCommnent() {

        // given
        String category = "질문";
        String title = "안녕하세요 JPA에 대해 질문이 있습니다";
        String createId = "KAKAO00001";
        Date createTime = new Date(System.currentTimeMillis());
        Date lastEditTime = new Date(System.currentTimeMillis());
        String content = "안녕하세요, 질문이 있어서 게시물을 작성합니다. \nJPQL은 이렇게 쓰는게 맞나요?";
        int viewCount = 0;

        Posts savedPosts = postsRepository.save(
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

        Long postId = savedPosts.getPostId();

        String comment = "넹 맞아여";
        String createId2 = "NAVER00001";
        Boolean isSecret = false;
        Date lastEditTime2 = new Date(System.currentTimeMillis());

        commentsRepository.save(
                Comments.builder()
                        .postId(postId)
                        .content(comment)
                        .createId(createId2)
                        .lastEditTime(lastEditTime2)
                        .secretYn(isSecret)
                        .build()
        );

        // when
        List<Comments> commentsSelected = commentsRepository.findByPostId(postId);

        // then
        Comments savedComment = commentsSelected.get(0);
        assertThat(savedComment.getContent()).isEqualTo(comment);
        assertThat(savedComment.getPostId()).isEqualTo(postId);
        assertThat(savedComment.getSecretYn()).isEqualTo(isSecret);
    }
}
