package notice.board.notice_board.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.post.dto.PostRequest;
import notice.board.notice_board.domain.post.entity.Post;
import notice.board.notice_board.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        postRepository.save(post);
        return post.getPostId(); // API 명세서에 따라 id를 반환해줄거임
    }

}
