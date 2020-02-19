## [인텔리J+Gradle+SpringBoot2개발] 기존 깃허브 소스 확장<br> 스프링부트(이하 스프링으로 표기)을 이용한 블로그 만들기
***
사용된 스프링부트 버전: 2.2.0.RELEASE(스프링프레임웍 버전: 5.x)
스프링 라이선스는 Apache 2.0 라이선스를 따릅니다.[웹사이트](https://spring.io/)<br>
부트스트랩/AdminLTE/기타등등<br>책 내에서 사용된 외부 오픈소스의 경우 원 오픈소스의 라이선스 정책을 유지합니다.
[라이센스 보기](https://github.com/spring-projects/spring-boot/blob/master/LICENSE.txt)
***
>작업일자(아래): 20200219
### 깃허브 소스 내용을 기반으로 마이그레이션.
- h2(embeded메모리DB) 지원 코드 추가(기본은 h2) /build.gradle 설정추가 만으로 스키마 sql 파일 없이 CRUD 가능.
- 개발환경: 인텔리J+그래들빌드, OAuth2로 로그인 인증 구현
- 작업내역: 메이븐 빌더기반 -> 그래들 빌더기반으로 변경, 스프링부트1.4 -> 스프링부트2.x 로 마이그레이션