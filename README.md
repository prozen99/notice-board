## Notice Board

## 9/11 Notice Board 프로젝트 1일차 
- 프로젝트 초기 설정인 Entity 구성 (Member,Comments,Post)
- 전통적인 방식의 (email,password  ) 를 이용한 회원가입 구현
- SecurityConfig 에서 비밀번호 Encoder와 , requestMathcer 설정

## 9/12 Notice Board 프로젝트 2일차
- Jwt 토큰에 대해서 공부함
- JwtTokenProvider를 구성함 
-도움을 받은 링크 https://velog.io/@musimco/JWT-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%93%B0%EC%9E%90

## 9/13 Notice Board 프로젝트 3일차
- postService, PostController,PostRepository 구성함 (게시글 관련을 위해서 )
- SecurityConfig에 Filter 설정을 안해줘서 403 에러 뜬거 문제 해결
- JWT 토큰 파싱할떄 ClamisJwt 말고 ClaimsJws사용 해야함 ( 둘의 차이는 서명 차이 라는 사실을 알게됨)
- 지금은 Filter 문제이기 때문에 디버깅 관련 문제를 이용해야 한다는 것을 깨달았음. 
  
