<h1 align="center"> Cus+ard CRM 백엔드  </h1>

* 이커머스 사이트의 관리자가 고객 정보 및 이커머스 운영 정보를 확인하는데 사용되는 백엔드 프로젝트 입니다.
* 이커머스 사이트에서 수집된 고객들의 행동 데이터들을 집계하고 분석하여 제공하는 역할을 수행합니다.
* 메인 대시보드, 고객 정보, 고객 상세 정보, 1:1 문의 페이지 기능을 제공합니다.


## ⛓️ Stacks
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">
<img src="https://img.shields.io/badge/java-F7DF1E?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-000000?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/jsonwebtokens-FCC624?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
<img src="https://img.shields.io/badge/centos-262577?style=for-the-badge&logo=centos&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">

## 프로젝트 설계

### 💾 이커머스 ERD
<img src=https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/96675421/649847d1-ceae-4fa0-bd8c-a17242fcb75a>

### 🚧 이커머스 릴레이션 스키마
<img src=https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/96675421/8277a174-eafd-4107-b2a3-ec517d2941a7>

* 이커머스 사이트와 고객 관리 사이트는 DB를 공유하는 구조이다.
* 이커머스 사이트에서 고객의 정보, 고객의 로그인 정보, 상품 조회/검색/구매 내역, 1:1 문의 작성 내역을 저장한다.
* 고객 관리 사이트에서는 해당 정보들을 집계하여 대시보드로 전달한다.
    * 메인 대시보드에 표시되는 시간대 별 로그인 횟수, 일일 구매 금액, 카테고리 별 구매 금액, 휴면 회원 재접속율, 고객 상세 페이지에서 표시되는 고객이 작성한 문의 목록, 고객의 구매 정보 등을 계산한다.
* 답변: 이커머스 사이트에서 작성된 문의들을 답변 완료, 답변 대기 상태로 구분하며 확인할 수 있으며, 답변 대기 문의에 대한 답변을 작성할 수 있다.
* 푸시 알림: 이커머스 사이트에 로그인 시 발송된 푸시 알림 내역이 저장되어있다.
* 쿠폰: 관리자가 입력한 할인율, 카테고리가 지정되어 쿠폰 테이블에 저장됩니다.
* 쿠폰 발급: 스케쥴러를 통해 지정된 시간에 입력된 조건(고객 등급, 휴면 기간 등)에 맞는 고객 리스트를 검색해서 지정된 쿠폰(앞서 쿠폰 테이블에 저장된 쿠폰)을 발급한다.(쿠폰 보유 테이블의 count가 증가한다.)
* 이메일: 고객에게 발송된 이메일이 저장되며, 상품 추천 메일과 쿠폰 발급 알림 메일 두가지가 있다. 쿠폰 발급 알림은 쿠폰 발급과 동시에 스케쥴러를 통해 실행된다.