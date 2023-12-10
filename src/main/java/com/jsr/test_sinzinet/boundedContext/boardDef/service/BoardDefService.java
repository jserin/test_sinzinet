package com.jsr.test_sinzinet.boundedContext.boardDef.service;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.boardDef.repository.BoardDefRepository;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardDefService {
    private final BoardDefRepository boardDefRepository;

    public RsData<BoardDef> create(String boardCd, String boardNm) {
        BoardDef boardDef = BoardDef.builder()
                .boardCd(boardCd)
                .boardNm(boardNm)
                .build();
        boardDefRepository.save(boardDef);

        return RsData.of(
                "S-1","게시판분류 생성 완료", boardDef
        );
    }

    public Optional<BoardDef> findByBoardCd(String boardCd) {
        return boardDefRepository.findByBoardCd(boardCd);
    }
}
