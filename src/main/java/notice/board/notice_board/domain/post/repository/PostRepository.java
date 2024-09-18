package notice.board.notice_board.domain.post.repository;

import notice.board.notice_board.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p JOIN FETCH p.author LEFT JOIN FETCH p.comments c LEFT JOIN FETCH c.author WHERE p.postId = :postId")
    Optional<Post> findByIdWithComments(@Param("postId") Long postId);

    
}
