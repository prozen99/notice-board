package notice.board.notice_board.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.comment.dto.CommentRequest;
import notice.board.notice_board.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Void> createComments(@PathVariable Long postId, @RequestBody CommentRequest request)
    {
        commentService.createComment(postId,request);
        return ResponseEntity.status(201).build();
    }
    @PostMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> EditComments(@PathVariable Long postId,@PathVariable Long commentId,@RequestBody CommentRequest request)
    {
        commentService.EditComments(postId, request, commentId);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComments(@PathVariable Long postId,@PathVariable Long commentId)
    {
        commentService.deleteComments(postId,commentId);
        return ResponseEntity.status(204).build();
    }

}
