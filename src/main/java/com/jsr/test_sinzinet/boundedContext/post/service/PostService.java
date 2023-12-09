package com.jsr.test_sinzinet.boundedContext.post.service;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 전체글 번호 내림차순으로 조회
    public List<Post> findAll() {
        return postRepository.findAllByOrderByPostNoDesc();
    }

    // 단건 조회
    public Optional<Post> findByPostNo(Integer postNo) {
        return postRepository.findByPostNo(postNo);
    }

    public RsData<Post> create(BoardDef boardDef, String postSj, String postCn, String regstrId) {
        Post post = Post.builder()
                .boardDef(boardDef)
                .postSj(postSj)
                .postCn(postCn)
                .regstrId(regstrId)
                .regDt(LocalDateTime.now())
                .build();
        postRepository.save(post);

        return RsData.of(
                "S-1","게시글 생성 완료", post
        );
    }

}
