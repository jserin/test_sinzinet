package com.jsr.test_sinzinet.boundedContext.tag.service;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import com.jsr.test_sinzinet.boundedContext.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    // 태그 생성
    public RsData<Tag> create(BoardDef boardDef, String name) {
        Tag tag = Tag.builder()
                .boardDef(boardDef)
                .tag(name)
                .build();
        tagRepository.save(tag);

        return RsData.of(
                "S-1","태그 생성 완료", tag
        );
    }

    // 태그와 게시물 분류로 검색
    public Optional<Tag> findByTagAndBoardDef(String tag, BoardDef boardDef) {
        return tagRepository.findByTagAndBoardDef(tag, boardDef);
    }
}
