package notice.board.notice_board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PostRequest {
    private final String title;//변경 되면 안되니까 final
    private final String content;//
}
