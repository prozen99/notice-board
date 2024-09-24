package notice.board.notice_board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import notice.board.notice_board.domain.post.entity.Post;

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
    public static PostResponse toResponse(Post post){
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getAuthor(),
                (long)post.getComments().size()
        );
    }
}
