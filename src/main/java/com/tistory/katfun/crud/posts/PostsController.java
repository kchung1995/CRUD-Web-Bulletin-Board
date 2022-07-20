package com.tistory.katfun.crud.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    // 게시물 목록 조회
    @GetMapping("/posts")
    public void selectPostsList() {
        // 조회할 때 받는 검색조건, 페이징 등의 정보가 있을 것
    }

    // 게시물 내용 조회
    @GetMapping("/posts/{postId}")
    public PostsResponseDto view (@PathVariable Long postId) {
        // 조회할 권한이 있다면 게시글 내용을 조회한다.
        return postsService.viewPost(postId);
    }

    // 게시물 추가
    @PostMapping("/posts/{postId}")
    public Long addPost(@RequestBody PostsSaveRequestDto requestDto, @PathVariable String postId) {
        // 로그인 한 상태라면, 누구나 글을 쓸 수 있다.
        // 회원 ID, 게시판 ID를 키값으로 한다.
        return postsService.savePost(requestDto);
    }

    // 게시물 수정
    @PatchMapping("/posts/{postId}")
    public Long editPost(@PathVariable Long postId, @RequestBody PostsUpdateRequestDto requestDto) {
        // 1. 본인이 해당 게시물의 생성자이거나, 2. 관리자라면
        // 수정할 수 있다.
        // 해당 권한 체크 로직 구현 필요
        return postsService.updatePost(postId, requestDto);
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        // 1. 본인이 해당 게시물의 생성자이거나, 2. 관리자라면
        // 수정할 수 있다.
        // 해당 권한 체크 로직 구현 필요
        postsService.deletePost(postId);
    }
}
