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


## 9/15 Notice Board 프로젝트 4일차
- userDetailsService를 직접 지정해주지 않아서 inMemoryUserDetailsService 가 JwtTokenProvider와 JwtTokenFilter에 순환참조를 일으킴
- Post 기능 (title,content) 를 Json으로 쏴주는 과정 확인 그리고 13일에 하지 못한 디버깅을 완료하고 포스팅 했음
- 해당 inMemoryUserDetailsService 문제와 , ParseClaimJws 와 ParseClaimJwt 에 관한 디버깅 리뷰
- https://www.notion.so/d3d5a6cf1b9f45a6a2d2f47993006acc

## 9/17 Notice Board 프로젝트 5일차 
-![image](https://github.com/user-attachments/assets/54a36653-fbc0-4c87-8bd8-4cb0ec5ac337)
- 사진의 API 명세서중 2개의 API 구현 ( 게시글 전체 조회 , 게시글 구성 ) 
- 글을 쓰는 사람 ( 시큐리티 컨텍스트에서 인증을 받은 사람의 username)을 이용해서 post 구현했음
- 공부하면서 배운 내용은 노션링크
- https://www.notion.so/8-9-17-82868b8e1e6648108fd82c820ef6cf59

## 9/18 Notice Board 프로젝트 6일차
-![image](https://github.com/user-attachments/assets/ff88a0e7-ed2e-4713-9c04-b2d9435baf28)
-게시글 상세조회 API 개발 (Controller Service 계층에 메서드 추가 )
- 공부하면서 배운내용 노션링크
- https://www.notion.so/9-9-18-105264df543380219274fdebc4bd2060
