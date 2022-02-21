package com.tistory.katfun.crud.web;

import com.tistory.katfun.crud.domain.posts.Posts;
import com.tistory.katfun.crud.domain.posts.PostsRepository;
import com.tistory.katfun.crud.web.dto.PostsSaveRequestDto;
import com.tistory.katfun.crud.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void Posts_Registered() throws Exception {
        //given
        String title = "title";
        String content = "content";
        String author = "kchung1995@gmail.com";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(responseEntity.getBody() > 0L);

        List<Posts> all = postsRepository.findAll();
        assertEquals(title, all.get(0).getTitle());
        assertEquals(content, all.get(0).getContent());
    }

    @Test
    public void Posts_edited() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<PostsUpdateRequestDto>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(responseEntity.getBody() > 0L);

        List<Posts> all = postsRepository.findAll();
        assertEquals(all.get(0).getTitle(), expectedTitle);
        assertEquals(all.get(0).getContent(), expectedContent);
    }
}
