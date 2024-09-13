package notice.board.notice_board.domain.member.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
