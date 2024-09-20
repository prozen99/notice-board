package notice.board.notice_board.domain.post.dto;

import lombok.Getter;

@Getter
public class PostEditRequest {
    private String title;
    private String content;
}
