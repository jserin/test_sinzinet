package com.jsr.test_sinzinet.boundedContext.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer postNo;
    @ManyToOne
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;
    private String postSj;
    private String postCn;
    private String regstrId;
    @CreatedDate
    private LocalDateTime regDt;
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostTag> postTagList = new ArrayList<>();
}
