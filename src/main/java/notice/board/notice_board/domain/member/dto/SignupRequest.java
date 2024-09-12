package notice.board.notice_board.domain.member.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}
