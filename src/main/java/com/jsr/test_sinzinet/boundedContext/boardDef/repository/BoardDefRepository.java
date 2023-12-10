package com.jsr.test_sinzinet.boundedContext.boardDef.repository;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardDefRepository extends JpaRepository<BoardDef, String> {
    Optional<BoardDef> findByBoardCd(String boardCd);
}
