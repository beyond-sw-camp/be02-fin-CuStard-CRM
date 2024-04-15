<h1 align="center"> Cus+ard 쇼핑몰 백앤드  </h1>

* 전체 프로젝트에서 이커머스부분을 보여주기 위한 백엔드 프로젝트입니다.
* 이커머스 부분은 실제로 데이터가 수집될 수 있고, 고객 관리 백엔드 프로젝트에서 고객에 대한 프로모션을 진행할 경우 다시 이커머스 부분에서 보여줄 수 있는 형태입니다.
* 이커머스 프로젝트를 통해 고객들이 이용할 수 있는 검색, 구매, 결제 등의 기능을 제공하고 있습니다.


## ⛓️ Stacks
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">
<img src="https://img.shields.io/badge/java-F7DF1E?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-000000?style=for-the-badge&logo=springsecurity&logoColor=white">
<br>
<img src="https://img.shields.io/badge/jsonwebtokens-FCC624?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
<img src="https://img.shields.io/badge/centos-262577?style=for-the-badge&logo=centos&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">

## API 명세서

<a href="https://www.notion.so/API-2e704a21c39342b5a6d7356af08e5f84">API 명세서 </a>


## ✒️ 이커머스 ERD
<details open>
  <summary>아키텍처 이미지</summary>
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/78153440/0333b7d4-a56a-4e8d-a0bb-938ce3e9feb3">

* 위 ERD 사진은 전체 백엔드 ERD이다.
* 그 중 파란색으로 된 ERD가 이커머스 관련 ERD이며 기능으로는 쿠폰 보유 확인, 주문, 로그 저장, 검색, 1:1 문의 작성 등이 있다.
* 노란색 ERD는 고객관리 프로젝트 관련 ERD 이며, 쿠폰 생성, 쿠폰 발급, 이메일 발송, 1:1 문의 관리 등이 있다.

</details>


## 🚧 이커머스 릴레이션 스키마

<details open>
  <summary>아키텍처 이미지</summary>
<img src=./img/RS.png>

*  ERD를 릴레이션 스키마로 바꾸면 위 사진과 같습니다.
</details>


## 🏗️시스템 아키텍처

<details open>
  <summary>아키텍처 이미지</summary>
  <img src=./img/ecormerce.png>

* 전체 아키텍처 중 관리자 백엔드 부분은 제외한 아키텍처 입니다.
* Custard e-commerce를 이용하는 일반 사용자는 홈페이지에 접속하고  이용하기 위해 nginx 웹서버에 요청을 보낸다.
* Nginx 웹서버는 이 요청을 톰캣 서버로 전달하고, 톰캣 서버에 실행 중인 Spring boot에서 알맞은 컨트롤러를 찾아가서 요청이 처리하게 된다.
* 상품 이미지 조화와 같은 특정 요청은 Amazon S3에서 이미지 파일을 가져와 다시 프론트엔드로 전달한다.
* 이미지 외의 다른 요청은 Maria DB 서버에 접근하여 처리한다. DB 서버는 Master-Slave 구조로 구성하여 데이터 복구과 저장이 가능하며, 부하 분산을 통해 안정적인 서비스를 제공한다.

</details>

## 📲소프트웨어 아키텍처

<a href="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/wiki/%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98">소프트웨어 아키텍처 페이지</a>