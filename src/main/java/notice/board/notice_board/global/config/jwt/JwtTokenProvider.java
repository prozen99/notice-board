package notice.board.notice_board.global.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret_Key}")
    private String secretKey;//비밀키 구성

    @Value("${jwt.validityInMilliseconds}")
    private long validityInMilliseconds;//유효기간 설정

    private Key key;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);//secretkey 디코드
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /*
    key생성을 위한P
    JwtTokenProvider에서 구현해야할 메서드
    토큰을 생성하는 createToken
    인증권한을 얻는 getAuthentication
    토큰에서 username을 추출하는 getUserName
    토큰을 인증하는 resolveToken
    토큰을 검증하는 validateToken
     */
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);//jwt 토큰의 내용 구하기
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token)//스프링 시큐리티 컨텍스트에서 token이 존재하면
    //인증 권한을 얻는거임
    {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token)//token을 파싱해서 token 내부의 subject 내용을 가져옴 그게 username임
    {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))//Bearer 토큰이면서 null이아니면
        {
            return bearerToken.substring(7);//Bearer 공백 까지 7칸 빼고 그뒤부터
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


}
