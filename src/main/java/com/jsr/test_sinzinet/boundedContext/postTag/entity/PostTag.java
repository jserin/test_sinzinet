package com.jsr.test_sinzinet.boundedContext.postTag.entity;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class PostTag {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer boardTagId;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;
    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;
}
