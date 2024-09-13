package notice.board.notice_board.domain.post.repository;

import notice.board.notice_board.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    
}
