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
    @GetMapping("/posts/selectPostsList")
    public void selectPostsList() {
        // 조회할 때 받는 검색조건, 페이징 등의 정보가 있을 것
    }

    // 게시물 내용 조회
    @GetMapping("/posts/viewPost")
    public void viewPost() {
        // 게시물의 고유 ID를 키 값으로, 열람 권한이 있다면 열람하도록 한다.
    }

    // 게시물 추가
    @PostMapping("/posts/addPost")
    public Long addPost(@RequestBody PostsSaveRequestDto requestDto) {
        // 로그인 한 상태라면, 누구나 글을 쓸 수 있다.
        // 회원 ID, 게시판 ID를 키값으로 한다.
        return postsService.savePost(requestDto);
    }

    // 게시물 수정
    @PutMapping("/posts/editPost/{postId}")
    public Long editPost(@PathVariable Long postId, @RequestBody PostsUpdateRequestDto requestDto) {
        // 1. 본인이 해당 게시물의 생성자이거나, 2. 관리자라면
        // 수정할 수 있다.
        // 해당 권한 체크 로직 구현 필요

        return postsService.update(postId, requestDto);
    }

    // ID값으로 게시물 찾기
    @GetMapping("/posts/findPost/{postId}")
    public PostsResponseDto findPostById (@PathVariable Long postId) {
        return postsService.findById(postId);
    }



    // 게시물 삭제
    public void deletePost() {
        // 1. 본인이 해당 게시물의 생성자이거나, 2. 관리자라면
        // 수정할 수 있다.
        //
    }
}
