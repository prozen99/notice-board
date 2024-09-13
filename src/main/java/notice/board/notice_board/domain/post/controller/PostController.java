package notice.board.notice_board.domain.post.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.post.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


}
