package com.jsr.test_sinzinet.boundedContext.tag.repository;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByTagAndBoardDef(String tag, BoardDef boardDef);
}
