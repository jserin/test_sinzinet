package com.jsr.test_sinzinet.boundedContext.post.controller;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService postService;


    //전체 글 내림차순 조회
    @AllArgsConstructor
    @Getter
    public static class PostsResponse {
        private final List<Post> posts;
    }

    @GetMapping(value = "")
    public RsData<PostsResponse> posts() {
        List<Post> posts = postService.findAll();

        return RsData.of(
                "S-1",
                "전체 글 조회 성공",
                new PostsResponse(posts)
        );
    }

    // 게시글 단건 조회
    @AllArgsConstructor
    @Getter
    public static class PostResponse {
        private final Post post;
    }

    @GetMapping(value = "/{postNo}")
    public RsData<PostResponse> post(@PathVariable(name = "postNo") Integer postNo) {
        return postService.findByPostNo(postNo).map(post -> RsData.of(
                "S-1",
                "%d번 게시물 조회 성공".formatted(postNo),
                new PostResponse(post)
        )).orElseGet(() -> RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(postNo),
                null
        ));
    }
}
