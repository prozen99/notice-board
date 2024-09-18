package notice.board.notice_board.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.comment.dto.CommentResponse;
import notice.board.notice_board.domain.member.entity.Member;
import notice.board.notice_board.domain.post.dto.PostDetailResponse;
import notice.board.notice_board.domain.post.dto.PostRequest;
import notice.board.notice_board.domain.post.dto.PostResponse;
import notice.board.notice_board.domain.post.entity.Post;
import notice.board.notice_board.domain.post.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUsername = ((UserDetails) principal).getUsername();
            } else {
                currentUsername = principal.toString();
            }
        }


        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(currentUsername);
        postRepository.save(post);
        return post.getPostId(); // API 명세서에 따라 id를 반환해줄거임
    }

    @Transactional
    public List<PostResponse> searchPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getTitle(),
                        post.getAuthor(),
                        (long) post.getComments().size()  // 댓글 수를 Long으로 변환하여 반환
                ))
                .collect(Collectors.toList());

    }

    @Transactional
    public PostDetailResponse getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다"));
        //Post의 Comments의 리스트를 CommentResponse로 변환
        List<CommentResponse> commentResponses = post.getComments().stream()
                .map(comment -> new CommentResponse(
                        comment.getCommentId(),
                        comment.getContent(),
                        comment.getAuthor().getUsername()
                ))
                .toList();
        return new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                commentResponses//생성자에 넣기
        );
    }


}
