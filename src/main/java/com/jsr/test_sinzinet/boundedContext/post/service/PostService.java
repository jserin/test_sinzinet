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

    // 게시글 생성
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

    // 게시글 수정
    public RsData<Post> modify(Post post, String postSj, String postCn) {
        if (postSj != null) post.setPostSj(postSj);
        if (postCn != null) post.setPostCn(postCn);
        postRepository.save(post);

        return RsData.of(
                "S-1",
                "%d번 게시물이 수정되었습니다.".formatted(post.getPostNo()),
                post
        );
    }


}
