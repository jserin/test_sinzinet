package com.jsr.test_sinzinet.boundedContext.postTag.repository;

import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
}
