<h1 align="center"> Cus+ard 쇼핑몰 <img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/78153440/76fb4ada-d8e8-445e-9c30-ce944be4c52a" alt="logo" width="30px" height="30px"> 프론트엔드  </h1>


* custard 쇼핑몰 페이지입니다. 
* 5개의 카테고리의 상품(의류, 뷰티, 식품, 스포츠/레저, 가전)을 판매합니다. 
* 회원가입, 로그인, 상품 조회, 주문/결제, 1:1 문의, 쿠폰 확인 등의 기능이 있습니다. 
* 위 기능들은 로그를 남겨 CRM 관리자 홈페이지에서 집계/분석에 이용됩니다. 

## ⛓️ Stacks
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">
<img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E">
<img src="https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D">
<img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"/>
<img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"/>
<img src="https://img.shields.io/badge/Router-CA4245?style=for-the-badge&logo=Router&logoColor=white"/>


##  🎥 상세 기능

<details>
    <summary>일반 사용자 회원 가입</summary>

<p align="center">
<img width="80%" src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/78153440/ec883284-df19-4e9e-858c-fc4a0b103907">

* 이메일과 비밀번호를 통해 로그인 할 수 있던 로그인 방식을 구현하기 위해 이메일과 비밀번호를 입력하는 것을 첫번째로 회원가입 내용으로 구성하였고
  물건을 주문하는 고객의 나이와 성별을 선택적으로 기입함으로써 나이와 성별에 따른 물건의 선호도를 조사할 수 있도록 구성해 보았습니다.
* 이 외에 물건을 배송받을 배송지와 서비스 운영에 필요한 약관에 대한 동의를 확인하는 체크박스로 회원가입 페이지를 구성해 보았습니다.
</p>

</details>

<details>
    <summary>일반 사용자 로그인</summary>

<p align="center">
<img width="80%" src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/78153440/03a55c04-6234-4312-b45d-39020021c1db">

* 이메일과 비밀번호를 통해 로그인 할 수 있으며 아직 가입되지 않은 회원을 위해 회원가입 버튼을 구성하였고 비밀번호를 잊은 회원의 경우 간편하게 찾아볼 수 있도록 비밀번호 재설정 버튼도 구성하였습니다.

* SNS 계정을 통해 간편 로그인 버튼도 구성해 놓아 추후 필요한 경우 로그인 방법의 확장도 고려해 볼 수 있겠습니다.
  또한 회원가입을 여러가지 이유로 인해 하지않고 주문을 원하는 고객을 위해
  비회원으로 주문하기 버튼을 구성해 주문번호와 이메일을 입력해 주문을 해볼 수 있도록 구성하였습니다.
</p>

</details>

<details>
    <summary>상품 검색 및 상세 조회</summary>

<p align="center">
<img width="80%" src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/78153440/91d632d4-4fcb-4843-aa72-09101bc3ab58">
</p>
</details>

<details>
    <summary>메인 페이지</summary>

<p align="center">
<img width="80%" src="">

* 이커머스 'CusTard'를 사용하는 고객들이 물건을 직관적으로 확인하고 구매 의사를 결정 할 수 있도록 구성하는 것을 목표로 메인 페이지를 구현해 보았습니다.
* 페이지를 통해 간편하게 물건을 확인하게 되고 다른 물건을 찾아보기 용이하도록 페이지 최상단에 검색창을 위치하게 하였고,
  항목별 물건을을 다음 버튼을 통해 간편하게 확인할 수 있도록 배치해 직관성을 올려보았습니다.

</p>
</details>


<details>

  <summary>카테고리 조회</summary>


</details>

<details>
    <summary> 1:1문의 내역 조회 및 작성</summary>

<p align="center">
<img width="80%" src="">

* 1:1 문의 글 작성 페이지에 문의 글 작성 버튼을 구성해 버튼을 누르면 문의글을 작성하는 페이지로 이동 할 수 있도록 하였고 문의 글 작성 페이지를 제목과 내용을 통해 관리자에게 문의하고자 하는 내용을 작성할 수 있게 구성해 보았습니다.
* 이 때 고객이 문의한 내용을 다른 고객이 쉽게 확인하지 못하고
  문의 글에 대한 식별을 위해 비밀번호를 입력 할 수 있도록 구성해 보았습니다.
</p>

</details>

<details>
    <summary>결제 및 결제 완료 페이지</summary>

* QR코드 페이지에서 3초간 핸드폰으로 결제 완료한 뒤 결제 완료 창이 나타납니다.
<p align="center">
<img width="80%" src="">
</p>
</details>









