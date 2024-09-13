package notice.board.notice_board.domain.member.service;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.member.dto.LoginRequest;
import notice.board.notice_board.domain.member.dto.SignupRequest;
import notice.board.notice_board.domain.member.entity.Member;
import notice.board.notice_board.domain.member.repository.MemberRepository;
import notice.board.notice_board.global.config.jwt.JwtTokenProvider;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Member registermember(SignupRequest request)//회원가입을 위한 메서드
    {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new ApplicationContextException("중복 된 이메일입니다");
        }
        String password = passwordEncoder.encode(request.getPassword());
        Member member = new Member(request.getUsername(), request.getEmail(), password);
        return memberRepository.save(member);
    }

    public String authenticate(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Not Found Email"));
        if(!passwordEncoder.matches(request.getPassword(),member.getPassword()))
        {
            throw new ApplicationContextException("비밀번호가 틀립니다");
        }
        return jwtTokenProvider.createToken(member.getEmail());
    }


}
