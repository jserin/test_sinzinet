package com.jsr.test_sinzinet.boundedContext.post.repository;

import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByPostNoDesc();

    Optional<Post> findByPostNo(Integer postNo);
}
