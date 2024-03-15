<template>
  <div class="login-container">
    <div class="content-container">
      <div class="text-with-image">
        <img src="../assets/custard-logo.png" alt="커스터드 로고">
      </div>
      <form id="loginForm" @submit.prevent="login">
        <input type="text" placeholder="이메일" id="email" v-model="loginForm.customerEmail" required>
        <input type="password" placeholder="비밀번호" id="password" v-model="loginForm.customerPwd" required>
        <br><br>
        <button type="submit">로그인</button>
      </form>
      <br>
      <div>
        <a href="/users/password/new">비밀번호 재설정</a>
      </div>
      <a href="/normal_users/new">회원가입</a>
      <br><br>
      <div class="confortLogin">SNS 계정으로 간편 로그인 / 회원가입</div>
      <div class="sns-buttons">
        <a href="/users/auth/facebook" class="facebook"><img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTAzMjlfNSAg%2FMDAxNTUzODM2ODA4MTky.-gS3ZoRn6NftLL0GUjuFUaDNRgoG9vAnH--zyNQIF1Ag.7tgGDNfnJlPGxaTGqye0f5cD0_HKnU6GNQ7wf1FbgZAg.JPEG.btf0c6dsc%2FDFGJSDF%253BLGJ%253BKJSF%253BGKLJR%253BKLDFG.gif&type=sc960_832_gif" alt="페이스북 로고"></a>
        <a href="/users/auth/kakao" class="kakao"><img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA3MjFfMjYy%2FMDAxNjI2ODMxODU0NzUx.KsijiA7OtIhygOW1opRzuVuxZeOyK-98jGSc0Ao6g6sg.p-mwUQH33OLvab3S-6fa4-bIJsJCdPzSPpRgWg2T8mkg.JPEG.aosmithkr%2F%25C4%25AB%25C4%25AB%25BF%25C0%25C5%25E5%25C0%25CC%25B9%25CC%25C1%25F6.jpg&type=sc960_832" alt="카카오 로고"></a>
        <a href="/users/auth/naver" class="naver"><img src="../assets/naversimbol.png" alt="네이버 로고"></a>
      </div>
      <div class="loginError">로그인에 문제가 있으신가요?</div>
      <hr class="line">
      <div class="serchOrder" @click="toggleOrderSearch()">비회원 주문 조회하기</div>
      <div class="order-search-container">
        <input type="text" placeholder="주문번호" id="orderNumber">
        <input type="text" placeholder="이메일" id="orderEmail">
        <br>
        <button type="button" @click="searchOrder()">주문 조회</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        customerEmail: '',
        customerPwd: '',
      }
    };
  },
  methods: {
    async login() {
      // 로그인 로직을 구현하세요.
    },
    toggleOrderSearch() {
      var orderSearchContainer = document.querySelector('.order-search-container');
      var isVisible = orderSearchContainer.style.maxHeight !== '0px';

      if (isVisible) {
        orderSearchContainer.style.maxHeight = '0';
        orderSearchContainer.style.opacity = '0';
      } else {
        orderSearchContainer.style.maxHeight = '200px'; // 필요에 따라 높이를 조절하세요.
        orderSearchContainer.style.opacity = '1';
      }
    },
    searchOrder() {
      var orderNumber = document.getElementById('orderNumber').value;
      var orderEmail = document.getElementById('orderEmail').value;
      console.log('주문번호:', orderNumber);
      console.log('이메일:', orderEmail);
    }
  }
};
</script>

<style scoped>
* {
  font-family: "NotoSansKR-Bold", "malgun gothic", AppleGothic, dotum, sans-serif;
}

body {
  width: 100%;
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.login-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  width: 100%;
  text-align: center;
}

.text-with-image {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  color: #494949;
  font-size: 13px;
}

.text-with-image img {
  width: 100px;
  height: auto;
  margin-right: -20px;
  border-radius: 20%;
  margin-bottom: 2%;
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
  background-color: #99154e;
}

.content-container {
  text-align: center;
  margin-top: 20px;
  background-color: #fff;
}

.content-container a {
  color: #333;
  text-decoration: none;
  margin: 0 10px;
  font-size: 12px;
}

.sns-buttons {
  margin-top: 20px;
}

.sns-buttons a {
  display: inline-block;
  margin: 0 13px;
  padding: 8px;
  text-decoration: none;
  color: #fff;
  border-radius: 50%;
  cursor: pointer;
}

.sns-buttons .facebook {
  background-color: #3b5998;
}

.sns-buttons .kakao {
  background-color: #fae100;
}

.sns-buttons .naver {
  background-color: #00c63b;
}

.confortLogin {
  font-size: 11px;
  color: #696969;
  margin-top: 10px;
}

.sns-buttons img {
  width: 20px;
  height: 20px;
  margin-right: 4px;
}

.line {
  margin-top: 25px;
  border: none;
  height: 1px;
  background-color: #c2c8cc;
}

.loginError {
  font-size: 10px;
  margin-top: 20px;
  margin-bottom: -5px;
  color: #c2c8cc;
}

.serchOrder {
  font-size: 11px;
  color: #696969;
  margin-top: 20px;
  cursor: pointer;
}

.order-search-container {
  opacity: 0;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s ease-out, opacity 0.3s ease-out;
  margin-top: 10px;
}

.order-search-container input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

</style>
