package notice.board.notice_board.domain.post.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.post.dto.PostIdResponse;
import notice.board.notice_board.domain.post.dto.PostRequest;
import notice.board.notice_board.domain.post.dto.PostResponse;
import notice.board.notice_board.domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostIdResponse> post(@RequestBody PostRequest request)
    {
        Long postId = postService.createPost(request);
        return ResponseEntity.ok(new PostIdResponse(postId));
    }
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPost()// post 경로의 get요청
    {
        List<PostResponse> postResponses = postService.searchPosts();
        return ResponseEntity.ok(postResponses);
    }


}
