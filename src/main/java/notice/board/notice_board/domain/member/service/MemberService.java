package notice.board.notice_board.domain.member.service;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.member.dto.SignupRequest;
import notice.board.notice_board.domain.member.entity.Member;
import notice.board.notice_board.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member registermember(SignupRequest request)//회원가입을 위한 메서드
    {
        String password= passwordEncoder.encode(request.getPassword());
        Member member=new Member(request.getUsername(),request.getEmail(),password);
        return memberRepository.save(member);
    }


}
