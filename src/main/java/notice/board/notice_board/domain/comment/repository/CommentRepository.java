package notice.board.notice_board.domain.comment.repository;

import notice.board.notice_board.domain.comment.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {

}
