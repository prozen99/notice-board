package notice.board.notice_board.domain.member.service;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.member.entity.Member;
import notice.board.notice_board.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("userEmail" + userEmail + "Not found"));
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
