package notice.board.notice_board.domain.post.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.post.dto.PostRequest;
import notice.board.notice_board.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<Long> post(PostRequest request)
    {
        Long postId = postService.createPost(request);
        return ResponseEntity.ok().body(postId);
    }


}
