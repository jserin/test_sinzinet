package com.jsr.test_sinzinet.boundedContext.post.controller;

import com.jsr.test_sinzinet.base.rsData.RsData;
import com.jsr.test_sinzinet.boundedContext.boardDef.entity.BoardDef;
import com.jsr.test_sinzinet.boundedContext.boardDef.service.BoardDefService;
import com.jsr.test_sinzinet.boundedContext.post.entity.Post;
import com.jsr.test_sinzinet.boundedContext.post.service.PostService;
import com.jsr.test_sinzinet.boundedContext.postTag.entity.PostTag;
import com.jsr.test_sinzinet.boundedContext.postTag.service.PostTagService;
import com.jsr.test_sinzinet.boundedContext.tag.entity.Tag;
import com.jsr.test_sinzinet.boundedContext.tag.service.TagService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService postService;
    private final BoardDefService boardDefService;
    private final TagService tagService;
    private final PostTagService postTagService;

    //전체 글 내림차순 조회
    @AllArgsConstructor
    @Getter
    public static class PostsResponse {
        private final List<Post> posts;
    }

    @GetMapping(value = "")
    public RsData<PostsResponse> posts() {
        List<Post> posts = postService.findAll();

        return RsData.of(
                "S-1",
                "전체 글 조회 성공",
                new PostsResponse(posts)
        );
    }

    // 게시글 단건 조회
    @AllArgsConstructor
    @Getter
    public static class PostResponse {
        private final Post post;
    }

    @GetMapping(value = "/{postNo}")
    public RsData<PostResponse> post(@PathVariable(name = "postNo") Integer postNo) {
        return postService.findByPostNo(postNo).map(post -> RsData.of(
                "S-1",
                "%d번 게시물 조회 성공".formatted(postNo),
                new PostResponse(post)
        )).orElseGet(() -> RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(postNo),
                null
        ));
    }

    // 게시글 작성하기
    @Data
    public static class CreateRequest {
        @NotBlank
        private String postSj;
        @NotBlank
        private String postCn;
        @NotBlank
        private String regstrId;
        private String[] tagName;
    }

    @AllArgsConstructor
    @Getter
    public static class CreateResponse {
        private final Post post;
    }

    @PostMapping(value = "/{boardCd}", consumes = APPLICATION_JSON_VALUE)
    public RsData<CreateResponse> create(@PathVariable(name = "boardCd") String boardCd,
                                       @Valid @RequestBody CreateRequest createRequest) {
        BoardDef boardDef = boardDefService.findByBoardCd(boardCd).orElseThrow();
        RsData<Post> createRs = postService.create(boardDef, createRequest.getPostSj(), createRequest.getPostCn(), createRequest.getRegstrId());

        if (createRs.isFail()) return (RsData) createRs;

        // 태그가 있는 경우 실행
        if (createRequest.tagName != null) {
            for (String tag : createRequest.getTagName()) {
                // tag 테이블에 없으면 생성
                Optional<Tag> opTag = tagService.findByTagAndBoardDef(tag, boardDef);
                if (opTag.isEmpty()){
                    tagService.create(boardDef, tag);
                }

                // posttag 생성
                Optional<Tag> existTag = tagService.findByTagAndBoardDef(tag, boardDef);
                if (existTag.isPresent()) {
                    postTagService.create(createRs.getData(), boardDef, existTag.get());
                }
            }
        }

        return RsData.of(
                createRs.getResultCode(),
                createRs.getMsg(),
                new CreateResponse(createRs.getData())
        );
    }

    // 게시글 수정
    @Data
    public static class ModifyRequest {
        @NotBlank
        private String postSj;
        @NotBlank
        private String postCn;
        private String[] tagName;
    }

    @AllArgsConstructor
    @Getter
    public static class ModifyResponse {
        private final Post post;
    }

    @PatchMapping(value = "/{postNo}", consumes = APPLICATION_JSON_VALUE)
    public RsData<ModifyResponse> modify(
            @Valid @RequestBody ModifyRequest modifyRequest,
            @PathVariable(name = "postNo") Integer postNo
    ) {
        Optional<Post> opPost = postService.findByPostNo(postNo);

        if (opPost.isEmpty()) return RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(postNo),
                null
        );

        RsData<Post> modifyRs = postService.modify(opPost.get(), modifyRequest.getPostSj(), modifyRequest.getPostCn());

        List<PostTag> postTags = postTagService.findByPost(modifyRs.getData());

        // 게시글 태그 삭제
        if (postTags != null) {
            for (PostTag postTag: postTags) {
                postTagService.delete(postTag);
            }
        }

        // 게시글 태그 재생성
        if (modifyRequest.tagName != null) {
            for (String tag : modifyRequest.getTagName()) {
                // tag 테이블에 없으면 생성
                Optional<Tag> opTag = tagService.findByTagAndBoardDef(tag, opPost.get().getBoardDef());
                if (opTag.isEmpty()){
                    tagService.create(opPost.get().getBoardDef(), tag);
                }

                // posttag 생성
                Optional<Tag> existTag = tagService.findByTagAndBoardDef(tag, opPost.get().getBoardDef());
                if (existTag.isPresent()) {
                    postTagService.create(opPost.get(), opPost.get().getBoardDef(), existTag.get());
                }
            }
        }

        return RsData.of(
                modifyRs.getResultCode(),
                modifyRs.getMsg(),
                new ModifyResponse(modifyRs.getData())
        );
    }

    // 게시글 삭제
    @AllArgsConstructor
    @Getter
    public static class DeleteResponse {
        private final Post post;
    }
    
    @DeleteMapping(value = "/{postNo}")
    public RsData<DeleteResponse> delete(@PathVariable(name = "postNo") Integer postNo) {
        Optional<Post> opPost = postService.findByPostNo(postNo);

        if (opPost.isEmpty()) return RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(postNo),
                null
        );

        postService.delete(opPost.get());

        return RsData.of(
                "S-1",
                "%d번 게시물 삭제 성공".formatted(postNo),
                null
        );

    }
}
