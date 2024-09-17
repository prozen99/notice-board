package notice.board.notice_board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long postId;
    private String postTitle;
    private String author;
    private Long commentsCount;

    public PostResponse(Long postId) {
        this.postId = postId;
    }
}
