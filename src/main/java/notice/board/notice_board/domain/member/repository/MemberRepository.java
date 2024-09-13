package notice.board.notice_board.domain.member.repository;

import notice.board.notice_board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    //db에서 아이디 찾기
    boolean existsByEmail(String email);
    //db에서 아이디 존재하는지 찾기
}
