package notice.board.notice_board.domain.post.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.post.dto.*;
import notice.board.notice_board.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostIdResponse> post(@RequestBody PostRequest request) {
        Long postId = postService.createPost(request);
        return ResponseEntity.ok(new PostIdResponse(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPost()// post 경로의 get요청
    {
        List<PostResponse> postResponses = postService.searchPosts();
        return ResponseEntity.ok(postResponses);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable Long postId)
    {
        PostDetailResponse postDetail = postService.getPostDetail(postId);
        return ResponseEntity.ok(postDetail);//body값에 넣기
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> editPost(@PathVariable Long postId, @RequestBody PostEditRequest request)
    {
        postService.editPost(postId,request);//
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();//ResponseEntity 객체 생성
    }






}
