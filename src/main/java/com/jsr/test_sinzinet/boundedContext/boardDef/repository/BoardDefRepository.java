package com.jsr.test_sinzinet.boundedContext.boardDef.repository;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDefRepository extends JpaRepository<BoardDef, String> {
}
