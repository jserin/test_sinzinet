package com.jsr.test_sinzinet.boundedContext.boardDef.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class BoardDef {
    @Id
    private String boardCd;
    private String boardNm;
    @JsonIgnore
    @OneToMany(mappedBy = "boardDef", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "boardDef", cascade = CascadeType.REMOVE)
    private List<Tag> tagList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "boardDef", cascade = CascadeType.REMOVE)
    private List<PostTag> postTagList = new ArrayList<>();
}
