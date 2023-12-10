package com.jsr.test_sinzinet.base;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.boardDef.service.BoardDefService;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class initData implements ApplicationRunner {
    private final BoardDefService boardDefService;
    private final PostService postService;

    @Override
    public void run(ApplicationArguments args) {
        boardDefService.create("분류1", "공지");
        boardDefService.create("분류2", "자유");

        BoardDef boardDef = boardDefService.findByBoardCd("분류1").orElseThrow();
        postService.create(boardDef, "subject", "content", "user");
    }
}
