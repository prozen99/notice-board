package notice.board.notice_board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import notice.board.notice_board.domain.comment.dto.CommentResponse;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponse {
    private Long postId;
    private String postTitle;
    private String author;
    private String content;
    private List<CommentResponse> comments;

}
