package notice.board.notice_board.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import notice.board.notice_board.domain.comment.entity.Comments;

import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch =FetchType.LAZY,orphanRemoval = true)
    private List<Comments> comments;//댓글 리스트
}
