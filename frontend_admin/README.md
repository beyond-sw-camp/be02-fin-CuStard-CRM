<h1 align="center">고객 관리 서비스 Custard </h1>
<div align="center"> 
 <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Flw4rG%2FbtsFY8R9biY%2Fw14QE1yBsQMYzm57iEbPc1%2Fimg.png"/>
</div>

## 🧑‍🤝‍🧑 팀원
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/JungDongSeob"><img src="https://avatars.githubusercontent.com/u/58664027?v=4" width="100px;" alt=""/><br /><sub><b> 🐯팀장 : 정동섭</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/kangmoonhye"><img src="https://avatars.githubusercontent.com/u/122515113?v=4" width="100px;" alt=""/><br /><sub><b> 🐶팀원 : 강문혜</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/SongYeonBaek"><img src="https://avatars.githubusercontent.com/u/78153440?v=4" width="100px;" alt=""/><br /><sub><b> 🐱팀원 : 백송연</b></sub></a><br /></td>
     <tr/>
      <td align="center"><a href="https://github.com/Hyeon28"><img src="https://avatars.githubusercontent.com/u/96675421?v=4" width="100px;" alt=""/><br /><sub><b> 🐧팀원 : 이주현</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/dohyun0408"><img src="https://avatars.githubusercontent.com/u/149150345?v=4" width="100px;" alt=""/><br /><sub><b> 🐺팀원 : 김도현 </b></sub></a><br /></td>
    </tr>
  </tbody> 
</table>


## 프론트엔드 관리자 

* custard 이커머스 고객들을 관리하는 관리자 대시보드 입니다. 
* 크게 메인, 고객, 문의, 쿠폰 4개의 탭으로 구성되어 있으며 탭마다 이동하여 원하는 정보를 확인할 수 있습니다.
* 고객들의 수집된 데이터들은 차트와 표로 확인이 가능합니다. 

## ⭐ STACKS 
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=white">
<img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E">
<img src="https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D">
<img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
<img src="https://img.shields.io/badge/cent%20os-002260?style=for-the-badge&logo=centos&logoColor=F0F0F0">
<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"/>
<img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"/>
<img src="https://img.shields.io/badge/Router-CA4245?style=for-the-badge&logo=Router&logoColor=white"/>


## 💾 ERD
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/d7d06fc9-cdf0-4d9b-a3e1-e2adbcec0d3e">

## 🚧 릴레이션 스키마
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/fb61cc12-368c-4722-9e60-cd29a1cb7508">

## 🎥 상세기능 
<details>
    <summary>로그인</summary>
<p>관리자 페이지의 첫 화면인 로그인 페이지입니다. <br> 보안을 위해 사용자가 로그인을 하지 않으면 다른 페이지로 넘어갈 수 없게 했습니다. <br> 관리자는 부여받은 Id와 Pw를 이용해 로그인 후 사용할 수 있습니다</p>
    <img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/fde68211-d32f-4304-9c31-7fa54935b147">
   <br> 로그인 한 사용자가 오른쪽 상단의 로그아웃 버튼을 누르면 로그아웃이 되고 다시 로그인 화면으로 이동하게 됩니다.
<br>
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/1e2288f1-9d99-4bc0-ac84-419981be867c">
</details>

<details>
<summary>메인화면</summary>
로그인을 하면 이동되는 페이지 입니다. <br> 대시보드의 최상단에선 <b>방문자 수, 결제건 수, 신규유입, 휴면고객재접속, 하루 매출액</b>을 전일 대비로 음수면 빨간색과 마이너스로, 양수면 초록색과 플러스로 나타내어 한번에 파악할 수 있습니다. <br>
<b>카테고리 별 판매율</b> 차트에선 원 그래프로 각 카테고리의 판매 비율을 알 수 있으며 지난 14일 결제건 수와 총 판매금액도 함께 나타냅니다. <br>
<b>1:1 문의내역 처리현황</b>은 각 문의 내역의 답변상태가 뱃지형태로 나타내어 관리자가 답변/미답변 내역을 파악합니다 <br>
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/17aed92e-04ab-43eb-9296-563b730c3191">
<b>월별매출</b>은 각 월 매출을 막대그래프로 집계됩니다. 매출 집계는 전월 00일~ 당월 00일 입니다. <br>
<b>시간대 별 로그인</b>은 선그래프로 나타내어 가로축은 00시부터 24시이고 세로 축은 누적 로그인 횟수입니다. 
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/399840b5-6a51-4f9c-ac72-78fbc3860d70">
</details>

<details>
<summary>1:1 문의</summary>
고객이 남긴 문의에 대한 확인과 함께 답변을 남길 수 있습니다. 미답변 시에는 답변대기 뱃지와 함께 답변대기 상태로 남고 답변완료 후에는 답변완료 뱃지와 답변완료 탭으로 옮겨집니다.
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/c72632ea-eef3-4968-8947-050fd1648776">
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/4b8fa8ec-0683-40ee-b697-894550a2eb47">
</details>

<details>
<summary>고객</summary>
고객 탭으로 들어오면 고객들의 리스트가 뜹니다. 주문금액 순과 등급별로 정렬이 가능하며 선택한 등급에 해당되지 않는 고객들은 보이지 않습니다. <br>
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/68d1d4ba-f79d-408b-861c-5b49c03883cd">
열람을 희망하는 고객을 클릭하면 해당 고객의 세부데이터를 확인할 수 있습니다. <br>
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/010bcb5b-a879-47b6-8b12-6cd8fce485c6">
해당 고객의 데이터들과 남긴 문의, 로그인 시간, 카테고리 별 구매 금액과 함께 고객이 검색한 카테고리도 확인해 볼 수 있습니다.
<img src="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/db31698e-fa8f-49ce-a9b1-547cc42ba1d2">
</details>

<details>
<summary>쿠폰</summary>
</details>



