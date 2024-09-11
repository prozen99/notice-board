package notice.board.notice_board.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postId")
    private Long postId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="author")
    private String author;
}
