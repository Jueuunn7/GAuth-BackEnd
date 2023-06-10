# GAuth
GAuth는 학교 OAuth 서비스로, 교내 프로젝트나 서비스를 만들 때 필요한 학생들의 정보를 쉽게 얻을 수 있습니다.  
서비스 사용자는 로그인 및 회원가입을 간편하게 진행할 수 있습니다.

## Description
교내 프로젝트나 서비스가 생길 때마다 학생들의 정보를 각각의 DB에 담아서 사용하면 반복적인 회원가입 작업으로 불편함을 겪습니다.  
매년 바뀌는 학번들로 서비스의 GSM 학생 데이터들을 유지보수하기에 불편함이 생깁니다.  
따라서 교내에 OAuth를 도입하면 손쉽게 학생들의 정보를 조회할 수 있어 용이합니다.
학생들은 이용하려는 교내 서비스마다 계정을 새로 생성하지 않고 하나의 GAuth 계정으로 간편하게 로그인함으로써 더욱 편리해집니다.

## How to Clone?
```
 git clone https://github.com/GSM-MSG/GAuth-BackEnd.git
```

## How to use?
### GAuth 서비스
1. GAuth 서비스에서 회원가입, 로그인을 진행합니다.
2. sidebar에서 서비스 등록 탭을 눌러 서비스 등록 페이지로 이동합니다.
3. 서비스 등록 페이지에서 {서비스 이름, redirectURI, 서비스 사이트 URL}을 입력하여 서비스를 등록합니다.
### Oauth
1. [https://gauth.com/login?client_id=(clientID)&redirect_uri=(redirectURI)](https://gauth.com/login?client_id=(clientID)&redirect_uri=(redirectURI)) 에 유저가 접속합니다. (OAuth 로그인 페이지)
2. 로그인 성공 시 정보주는 동의를 받고 (redirectURI)?code=(code) 로 리다이렉트됩니다.
3. 리다이렉트된 곳(클라이언트 서버)에서 받은 코드를 써서 [https://server.gauth.com/](https://server.gauth.com/user) 에 POST method로 body에 code, clientId, clientSecret을 담아서 보내면 유저정보를 받을 수 있습니다
   <br>

자세한 설명은 [여기](https://gauth.co.kr/instruction)를 참고하세요

## Contributing
- 버그 제보: [이슈 트래커](https://github.com/GSM-MSG/GAuth-BackEnd/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc)에 제보할 버그를 작성합니다.
- 기능 제안: [이슈 트래커](https://github.com/GSM-MSG/GAuth-BackEnd/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc)에 제안하고 싶은 기능을 작성합니다.
- 코드 기여: GitHub에서 코드를 Fork하고, Pull Request를 보냅니다.