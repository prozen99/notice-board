package notice.board.notice_board.domain.member.repository;

import notice.board.notice_board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
