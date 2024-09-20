package notice.board.notice_board.domain.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.comment.dto.CommentRequest;
import notice.board.notice_board.domain.comment.entity.Comments;
import notice.board.notice_board.domain.comment.repository.CommentRepository;
import notice.board.notice_board.domain.member.entity.Member;
import notice.board.notice_board.domain.member.repository.MemberRepository;
import notice.board.notice_board.domain.post.entity.Post;
import notice.board.notice_board.domain.post.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createComment(Long postId, CommentRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//인증객체
        String currentUsername = null;
        if (authentication != null && authentication.isAuthenticated())//인증객체가 있고 , 인증까지 된상태
        {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUsername = ((UserDetails) principal).getUsername();
            } else {
                currentUsername = principal.toString();//Oauth2나 다른 로그인 방식을 이용할떄는 그대로
                //이메일이나 어떤 로그인 정보가 들어올수 있기 때문에 tostring으로 변경
            }
        }
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));
        //게시글 찾기
        Member member = memberRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
        //댓글 생성
        Comments comments = new Comments();
        comments.setContent(request.getContent());
        comments.setPost(post);//댓글과 게시글 연관설정 sql 조인과 비슷한 개념임
        //댓글은 post에 들어가니까
        comments.setAuthor(member);
        commentRepository.save(comments);
    }

    @Transactional
    public void EditComments(Long postId, CommentRequest request, Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//인증객체
        String currentUsername = null;
        if (authentication != null && authentication.isAuthenticated())//인증객체가 있고 , 인증까지 된상태
        {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUsername = ((UserDetails) principal).getUsername();
            } else {
                currentUsername = principal.toString();//Oauth2나 다른 로그인 방식을 이용할떄는 그대로
                //이메일이나 어떤 로그인 정보가 들어올수 있기 때문에 tostring으로 변경
            }
        }
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("댓글을 수정할 게시글이 없습니다"));

        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("수정할 댓글이 없습니다"));

        //현재 사용자가 댓글 사용자 인지 확인
        if (!comments.getAuthor().getUsername().equals(currentUsername))//댓글 작성자의 이름이
        //현재 유저네임과 같지 않으면
        {
            throw new RuntimeException("댓글 작성자만 수정 할 수 있습니다");
        }
        comments.setContent(request.getContent());
        commentRepository.save(comments);
    }

    @Transactional
    public void deleteComments(Long postId,Long commentId)
    {
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
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("삭제할 게시글이 없습니다"));
        Comments comments=commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("삭제할 댓글이 없습니다"));

        if(!comments.getAuthor().getUsername().equals(currentUsername))
        {
            throw new RuntimeException("댓글 작성자만 삭제 할 수 있습니다");
        }
        commentRepository.save(comments);
    }
}
