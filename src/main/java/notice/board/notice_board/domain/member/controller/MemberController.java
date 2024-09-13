package notice.board.notice_board.domain.member.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice_board.domain.member.dto.SignupRequest;
import notice.board.notice_board.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        memberService.registermember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}