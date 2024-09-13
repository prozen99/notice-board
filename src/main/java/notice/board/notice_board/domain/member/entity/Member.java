package notice.board.notice_board.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;


    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
