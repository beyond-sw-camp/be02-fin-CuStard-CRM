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
/* 로고 */
.logo{
  width: 50px;
  height: 50px;
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


.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 80vh;
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
  width: 80px;
  height: auto;
  margin-right: 5px;
  margin-left: -60px;
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
  background-color: #ffbe0e;
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
  margin-top: 0px;
  font-family: "Noto Sans", "malgun gothic", AppleGothic, dotum, sans-serif;
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
  color: #99154E; /* 호버 시의 컬러 변경 */
}
.order-details {
  text-align: left;
  margin-bottom: 20px;
  margin-top: 20px;
  color: #333;
}
.order-details p {
  margin: 10px 0;
}
.confirmation-message {
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 20px;
  color: #333;
  
}
.additional-actions a {
  display: inline-block;
  background-color: #FFBE0E;
  color: #fff;
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
