<template>
  <div class="login-container">
    <div class="content-container">
      <div class="text-with-image">
        <a href="/">
          <img src="../../public/img/logo.png" alt="Logo">
        </a>
        <h2>주문이 완료되었습니다!</h2>
      </div>
      <hr class="line">
      <div class="order-details">
        <p>주문번호:{{this.$route.query.impUid}} </p>
        <p>주문일시: {{this.$route.query.today}}</p>
        <p>상품 이름: {{this.$route.query.name}}</p>
        <p>총 결제금액: {{this.$route.query.price}}</p>
      </div>
      <div class="confirmation-message">
        <p>주문이 성공적으로 완료되었습니다. 감사합니다!</p>
      </div>
      <div class="additional-actions">
        <a href="/">홈으로 돌아가기</a>
      </div>
    </div>
    <footer>
      <ul class="bottom_util">
        <li><a href="">TEAM 2</a></li>
        <li><a href="">CRM</a></li>
        <li><a href="" target="_blank">CuStard</a></li>
      </ul>
      <div class="bottom_logo">
        <img src="../../public/img/logo.png" class="footlogo" >
      </div>
      <p class="copy_info"><span>서울 동작구 보라매로 87 4층 </span> <span>TEL : 02-000-0000</span>화이팅!</p>
      <p class="copy">HANHWA SW CAMP 2기</p>
    </footer>
  </div>
</template>

<script>
// 상세 페이지 컴포넌트의 스크립트 부분

export default {
  async mounted() {
    console.log(this.$route.query.impUid + " " +this.$route.query.today + " " + this.$route.query.name);
    console.log(this.$route);
  },
};

</script>


<style>
html, button, input, select, textarea {
  font-family: "Noto Sans", "malgun gothic", AppleGothic, dotum, sans-serif;
  cursor: pointer;
  width: 1284px;
  /* border: 1px solid red; */
  margin: 0 auto;
}
/* header */
.css-t79vuj {
  position: relative;
  width: 1050px;
  height: 100px;
  margin: 0px auto;
  letter-spacing: -0.3px;
}
.css-1xfyvd1 {
  display: flex;
  -webkit-box-align: center;
  font-size: 13px;
  justify-content: flex-end;
  /* border: 1px solid red; */
  margin-bottom: 10px;
}
.css-xygizb {
  display: block;
  color: rgb(95, 0, 128);
  cursor: pointer;
}
.css-1qgm48u:first-of-type {
  width: 1px;
  height: 13px;
  margin: 0px 12px;
  background-color: rgb(217, 217, 217);
}
.css-oyffzd {
  display: block;
  cursor: pointer;
}
.css-1qolcqm {
  line-height: 35px;
  position: relative;
}
.css-oyffzd {
  display: block;
  cursor: pointer;
}
.css-1lrerrk {
  width: 8px;
  height: 5px;
  background-image: url(https://res.kurly.com/pc/ico/1908/ico_down_16x10.png);
  background-size: cover;
  background-position: center center;
  display: inline-block;
  margin-left: 5px;
  margin-bottom: 1px;
}
.css-1ho29iy {
  position: absolute;
  right: 0px;
  top: 34px;
  width: 130px;
  padding: 3px 9px;
  border: 1px solid rgb(221, 221, 221);
  background-color: rgb(255, 255, 255);
}
.css-1qolcqm .menu {
  display: none;
}
.css-12olpw6 {
  display: block;
  height: 24px;
  line-height: 24px;
  cursor: pointer;
}
.css-r7wmjj {
  height: 100px;
  padding-top: 14px;
}
.css-boc80u {
  display: flex;
  /* -webkit-box-align: center; */
  align-items: center;
  height: 63px;
  width: 156px;
  /* border: 1px solid red; */
  position: relative;
  left: 50px
}
.gnb{
  /* background-color: blue; */
  display: flex;
  align-items: center;
}
.css-ywxmlw {
  position: relative;
  width: 30px;
  height: 30px;
  margin: 10px;
  bottom: 3px;
  background-image: url("../../public/icon/search.png");
  background-repeat: no-repeat;
  background-size : 60%;
  background-position : 5px 9px;
  border: none;
  background-color: #FFF;
}
.css-mxd3pm {
  flex-shrink: 0;
  margin-left: 20px;
  font-size: 18px;
  font-weight: normal;
  line-height: 1.33;
  letter-spacing: normal;
  color: rgb(181, 181, 181);
  cursor: pointer;
  border: none;
  background: none;
}
.css-mxd3pm.active, .css-mxd3pm:hover {
  font-weight: 500;
  color: rgb(255 190 14);
  border: none;
  background: none;
}
.css-11ntk83 {
  width: 300px;
  background-color: inherit;
  border: none;
  outline: none;
  font-size: 16px;
  letter-spacing: -0.33px;
}
.css-pqw0uk {
  position: relative;
  width: 1050px;
  margin: 0px auto;
}
.css-w444a2 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  position: relative;
  left: 300px;
  /* top: -55px; */
  width: 400px;
  height: 48px;
  padding-left: 14px;
  border: 1px solid #99154E;
  border-radius: 6px;
  background-color: rgb(255, 255, 255);
  box-shadow: rgb(247, 247, 247) 0px 0px 0px 1px inset;
}
.css-17tqugj {
  min-width: 1050px;
  letter-spacing: -0.3px;
  background-color: rgb(255, 255, 255);
  position: relative;
  box-shadow: rgba(0, 0, 0, 0.07) 0px 3px 4px 0px;
  width: 100%;
  z-index: 300;
}
/* 쿠폰 아이콘 */
.css-14vnom0 {
  width: 26px;
  height: 41px;
  margin-right: 5px;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Ctitle%3Eticket-percent-outline%3C/title%3E%3Cpath d='M14.8 8L16 9.2L9.2 16L8 14.8L14.8 8M4 4H20C21.11 4 22 4.89 22 6V10C20.9 10 20 10.9 20 12C20 13.11 20.9 14 22 14V18C22 19.11 21.11 20 20 20H4C2.9 20 2 19.11 2 18V14C3.11 14 4 13.11 4 12C4 10.9 3.11 10 2 10V6C2 4.89 2.9 4 4 4M4 6V8.54C5.24 9.26 6 10.57 6 12C6 13.43 5.24 14.75 4 15.46V18H20V15.46C18.76 14.75 18 13.43 18 12C18 10.57 18.76 9.26 20 8.54V6H4M9.5 8C10.33 8 11 8.67 11 9.5C11 10.33 10.33 11 9.5 11C8.67 11 8 10.33 8 9.5C8 8.67 8.67 8 9.5 8M14.5 13C15.33 13 16 13.67 16 14.5C16 15.33 15.33 16 14.5 16C13.67 16 13 15.33 13 14.5C13 13.67 13.67 13 14.5 13Z' /%3E%3C/svg%3E");
  cursor: pointer;
}
/* 찜 호버 했을 때 리스트 */
.css-c4pbxv {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  /* right: -200px;
  top: -49px; */
  /* border: 1px solid red; */
  width: 200px;
  justify-content: flex-end;
}
.css-ff2aah {
  position: relative;
  margin-left: 20px;
}
.css-mlddcv {
  position: relative;
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 1050px;
  height: 56px;
  margin: 0px auto;
}
.css-axtlq9 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  height: 100%;
}
.css-1k5gn9s {
  width: 16px;
  height: 14px;
  margin-right: 14px;
  background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNiAxNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0wIDBoMTZ2MS43SDBWMHptMCA2LjE1aDE2djEuN0gwdi0xLjd6bTAgNi4xNWgxNlYxNEgwdi0xLjd6IiBmaWxsPSIjMzMzIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiLz4KPC9zdmc+Cg==) 0px 0px / 16px 14px no-repeat;
}
.css-t75x7c {
  font-size: 16px;
  font-weight: 500;
  line-height: 20px;
  letter-spacing: -0.3px;
  color: rgb(51, 51, 51);
}
/* 쿠폰 드롭다운 */
.dropdown{
  position: relative;
  width: 60px;
  height: 125px;
  /* background-color: blueviolet; */
  padding: 0 10px 20px 10px;
  display: none;
  border: 1px solid rgb(223, 223, 223);
}
.coupondiv:hover > .css-14vnom0 .dropdown{
  position: absolute;
  top: 35px;
  right: -1px;
  overflow: scroll;
  display: block;
  background-color: white;
  width: 165px;
  padding: 10px;
}
.dropdown {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.dropdown::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.coupondiv{
  /* border: 1px solid red; */
  display: flex;
  transition: all 0.5s ease 0s;
}
.amount{
  margin-top: 10px;
}
.dropdown > li{
  margin: 9px 0px;
}
/* 로고 */
.logo{
  width: 50px;
  height: 50px;
}
/* footer */
footer {
  position: relative;
  clear: both;
  width: 100%;
  background: #c4c4c417;
  text-align: center;
  height: 200px;
  margin-top: 10%;
}
footer .bottom_util {
  border-bottom: solid 1px #4646462b;;
  border-top: solid 1px #4646462b;;
  padding: 16px 0;
}
footer .bottom_util li {
  display: inline-block;
  margin: 3px 19px;
}
footer .bottom_ccm {
  position: absolute;
  text-align: right;
}
footer .bottom_ccm span {
  display: block;
  width: 98%;
  max-width: 1178px;
  margin: auto;
}
footer .copy_info {
  width: 90%;
  max-width: 650px;
  margin: auto;
  margin-top: 45px;
  margin-bottom: 14px;
  font-size: 12px;
  color: #6C6C6C;
}
footer .copy_info span {
  display: inline-block;
  margin-right: 10px;
  padding-right: 10px;
}
footer .copy {
  padding-bottom: 15px;
  font-family: 'Nunito Sans', sans-serif;
  font-size: 12px;
  font-weight: 700;
  color: #6C6C6C;
}
footer .bottom_util li a {
  color: #818181;
}
footer .bottom_util li a em {
  color: #FFF;
  font-weight: 400;
}
/* 임시 */
.content{
  height: 100px;
  height: 500px;
  background-color: wheat;
  text-align: center;
}
.footlogo{
  width: 80px;
  height: 80px;
}
.bottom_logo{
  width: 82px;
  /* border: 1px solid red; */
  position: absolute;
  right: 75px;
  top: 95px
}
(255B)
https://res.kurly.com/pc/ico/1908/ico_down_16x10.png

5:34
*{
  font-family: 'GmarketSans';
  box-sizing: border-box; /* 상자 모델을 사용하여 푸터가 올바르게 표시되도록 설정 */
}
.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  flex-direction: column; /* 세로 방향으로 정렬하도록 추가 */
}
.text-with-image {
  display: flex;
  align-items: center;
  margin-bottom: 0px;
  text-align: center;
  top: 15px; /* 화면 상단에 정렬 */
  left: 10px; /* 화면 왼쪽에 정렬 */
  padding: 0px; /* 간격 조절 */
  z-index: 1000; /* 다른 요소들보다 위에 표시되도록 설정 */
  color: #494949;
  font-size: 14px;
  justify-content: center;
}
.text-with-image img {
  width: 130px;
  height: auto;
  margin-right: -15px;
  margin-left: -30px;
  border-radius: 20%;
}
.login-container h2 {
  color: #333;
}
.login-container input {
  width: 100%;
  padding: 10px;
  margin-bottom: 0px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.login-container button {
  width: 100%;
  padding: 10px;
  background-color: #99154E;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.5s;
}
.login-container button:hover {
  background-color: #99154E;
}
.content-container {
  max-width: 400px;
  width: 100%;
  text-align: center;
  padding: 20px;
  border: 2px solid #ccc;
  border-radius: 10px;
  margin-top: 15%;
}
.content-container a {
  color: #333;
  text-decoration: none;
  margin: 0 10px;
  font-size: 12px;
}
.confortLogin {
  font-size: 11px;
  color: #696969;
  margin-top: 0px;
  margin-bottom: -7px;
}
.sns-buttons img {
  width: 15px;
  height: 15px;
  margin-right: 3px;
}
.insertEmail {
  text-align: left;
  margin-top: -30px;
  margin-bottom: -15px;
  font-size: 15px;
  color: #494949;
}
.insertpassword {
  text-align: left;
  margin-bottom: -15px;
  font-size: 15px;
  color: #494949;
}
.insertpassword2 {
  text-align: left;
  margin-bottom: 5px;
  font-size: 10px;
  color: #494949;
}
.insertcheck {
  text-align: left;
  margin-bottom: -15px;
  font-size: 15px;
  color: #494949;
}
.insertnick {
  text-align: left;
  margin-bottom: -15px;
  font-size: 15px;
  color: #494949;
}
.insertnick2 {
  text-align: left;
  margin-bottom: 5px;
  font-size: 10px;
  color: #494949;
}
.signupfont {
  text-align: center;
  font-size: 14px;
  color: #494949;
}
.line {
  margin-top: 20px;
  border: 1px solid #ccc;
  height: 0px;
  background-color: #C2C8CC;
}
.emailAuth {
  margin-top: 4px;
  padding: 0px;
}
.emailAuth button {
  width: 100%;
  padding: 10px;
  background-color: rgb(247, 248, 250);
  color: rgb(194, 200, 204);
  border-color: rgb(218, 220, 224);
  border: solid 1px;
  border-radius: 4px;
  cursor: pointer;
}
.emailAuth button:hover {
  background-color: #99154E;
}
.consent-label {
  display: flex;
  align-items: center;
  text-align: left;
  margin-bottom: -10px;
  font-size: 15px;
  color: #494949;
}
.consent-options {
  border: 1px solid #ccc; /* Add border to wrap the consent options */
  padding: 10px;
}
.checkbox-label {
  display: flex;
  align-items: center;
  text-align: left;
}
.checkbox-wrapper {
  width: 20px; /* Set the width of the checkbox wrapper */
  height: 20px; /* Set the height of the checkbox wrapper */
  border: 1px solid #ccc; /* Add border to create a box */
  margin-right: 10px; /* Add margin to separate the box from text */
  display: flex;
  align-items: center;
  justify-content: center;
}
.consent-text {
  font-size: 11px;
  color: #494949;
}
.additional-consent {
  font-size: 9px;
  opacity: 0.7; /* 연하게 만들기 위한 투명도 조절 */
}
a {
  color: #333;
  text-decoration: none;
  transition: color 0.3s; /* 컬러 전환에 대한 트랜지션 효과 추가 */
}
a:hover {
  color: #00AB03; /* 호버 시의 컬러 변경 */
}
.order-details {
  text-align: left;
  margin-bottom: 20px;
  margin-top: 20px;
}
.order-details p {
  margin: 10px 0;
}
.confirmation-message {
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 20px;
}
.additional-actions a {
  display: inline-block;
  background-color: #FFBE0E;
  color: white;
  padding: 10px 20px;
  text-decoration: none;
  border-radius: 5px;
  margin-right: 10px;
  transition: all 0.5s;
}
.additional-actions a:hover {
  background-color: #99154E;
}
</style>