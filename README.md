# community_feed

간단히 사용자들과 소통할 수 있는 게시판 서비스

[ 개인 프로젝트 목표 ]
- 조회 부분에서 전과 후를 비교하여 성능 개선 할수 있는 부분을 정리해가면서 적용해보기
- 추후 기능에서 좋아요 기능은 대부분은 Redis를 활용해서 적용 하던데 그 이유를 블로그를 적힌걸 많이 보았으니까 
  활용 전 활용 후 코드를 짜보고 왜 사용해야되는지 파악 하기

## 프로젝트 기능 및 설계
- [ ] 회원가입 기능
- 사용자는 회원가입 시 이름, 이메일(unique), 비밀번호(unique)을 입력할수 있다.
- 일반적으로 모든 사용자는 회원가입 시 ACTIVE 상태와 USER 권한 (일반) 을 지닌다


- [ ] 로그인 기능
- 로그인시 회원가입때 입력한 이메일과와 비밀번호가 일치해야한다.
- 로그인 시 jwt 토큰이 발급된다. 토큰의 페이로드는 이메일이 포함된다.
- 추가적으로 구글 소셜 로그인을 할수 있다.

- [ ] 회원 탈퇴 기능
- 회원 탈퇴 시 회원정보의 비밀번호가 일치해야한다.
- 회원 탈퇴 시 상태가 변한다 (DB에는 남아있고 상태가 ACTIVE - > INACTIVE).
- 회원 탈퇴 시 작성했던 게시글 ,댓글들이 사라진다.


- [ ] 게시글 작성 기능
- 게시글 작성은 로그인한 사용자만 작성 할 수 있다.
- 게시글 작성 시 제목(텍스트),내용(텍스트),이미지(PNG,JPG)를 입력할수 있다.
- 이미지에 대한 파일은 S3에 저장하고 DB에는 url만 저장이 된다.

- [ ] 게시글 목록 조회 기능
- 게시글 목록 조회는 모든 사용자가 조회 할 수 있다.
- 게시글 목록 조회시 응답에는 게시글 제목과, 작성일 OR 수정일이 있다.
- 게시글은 최신순으로 기본 정렬 된다. 
- 게시글은 수가 많을 수 있으므로 paging 처리를 한다.
- 게시글 목록은 작성자명 또는 게시물 제목으로 검색 할수 있다. 


- [ ] 게시글 상세 조회 기능
- 게시글 상세 조회는 로그인한 사용자만 볼수 있다.
- 게시글 상세 조회시 응답에는 제목과 작성일 댓글의 정보가 필요하다.


- [ ] 게시글 수정 기능
- 게시글 수정은 자신이 작성한 게시글 건 한에서 수정이 가능 하다.
- 수정 시 사진이 아닌 텍스트만 수정 할수 있다.


- [ ] 게시글 삭제 기능
- 게시글 삭제는 자신이 작성한 게시글 건 한에서 삭제가 가능 하다.
- 게시글 삭제 시 해당 게시글에 대한 댓글이 사라진다.


- [ ] 댓글 작성 기능
- 댓글 작성은 로그인한 사용자만 작성 할 수 있다.
- 사용자는 댓글 내용(텍스트)를 작성할 수 있다.

- [ ] 댓글 목록 기능
- 특정 게시글 조회 시 댓글 목록도 함께 조회가 된다. 다만 댓글은 많을 수 있기 때문에 별도의 API로 구성한다.
- 댓글은 최신순으로만 정렬 된다.
- 댓글은 수가 많을 수 있으므로 paging처리를 한다.
- 댓글 목록 조회시 응답에는 게시글 제목과, 작성일 OR 수정일이 있다.


- [ ] 댓글 수정 기능
- 댓글 수정은 자신이 작성한 댓글 건 한에서 수정이 가능 하다.


- [ ] 댓글 삭제 기능
- 댓글 삭제는 자신이 작성한 댓글 건 한에서 수정이 가능 하다.

## ERD

![Image](https://github.com/user-attachments/assets/a5b1afca-b03b-45b9-a697-3310966643ab)

## Trouble Shooting

[https://loving-selenium-29c.notion.site/1965676ddb23806588c6f1d4058b1832](https://loving-selenium-29c.notion.site/1965676ddb2380ee9e34e3a8a4788d92)

## Tech Stack

<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

