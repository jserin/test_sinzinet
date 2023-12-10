package com.jsr.test_sinzinet.boundedContext.postTag.service;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import com.jsr.test_sinzinet.boundedContext.postTag.repository.PostTagRepository;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostTagService {
    private final PostTagRepository postTagRepository;

    // 게시물 태그 생성
    public RsData<PostTag> create(Post post, BoardDef boardDef, Tag tag) {
        PostTag postTag = PostTag.builder()
                .post(post)
                .boardDef(boardDef)
                .tag(tag)
                .build();
        postTagRepository.save(postTag);

        return RsData.of(
                "S-1","게시물 태그 생성 완료", postTag
        );
    }

    // 게시글 태그 삭제
    public void delete(PostTag postTag) {
        postTagRepository.delete(postTag);
    }

    public List<PostTag> findByPost(Post post) {
        return postTagRepository.findByPost(post);
    }
}
