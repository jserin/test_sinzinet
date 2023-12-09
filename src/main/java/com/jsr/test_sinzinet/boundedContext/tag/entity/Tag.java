package com.jsr.test_sinzinet.boundedContext.tag.entity;

import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
public class Tag {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer tagNo;
    private String tag;
    @ManyToOne
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<PostTag> postTagList = new ArrayList<>();
}
