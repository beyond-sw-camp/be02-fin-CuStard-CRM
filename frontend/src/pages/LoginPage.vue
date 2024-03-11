<template>
  <div class="login-container">
    <div class="content-container">
      <form id="loginForm" @submit.prevent="login">
        <!-- v-model을 loginForm.customerEmail로 수정 -->
        <input type="text" placeholder="이메일" id="email" v-model="loginForm.customerEmail" required>
        <input type="password" placeholder="비밀번호" id="password" v-model="loginForm.customerPwd" required>
        <br><br>
        <!-- @click을 @submit.prevent로 변경하여 form 제출 시 login 메소드를 호출 -->
        <button type="submit" @click="login()">로그인</button>
      </form>
    <br>
  </div>
</div>
</template>

<script>
import { useCustomerStore } from '@/stores/useCustomerStore';

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
      const customerStore = useCustomerStore(); // 스토어 직접 사용
      const result = await customerStore.login(this.loginForm);

      // 로그인 응답에서 status 값을 확인
      if (result && result.status !== false) {
        // status가 0이 아니면 로그인 성공 처리
        const customerIdx = sessionStorage.getItem('customerIdx');
        localStorage.setItem('customerIdx', customerIdx);

        console.log(`로그인 성공: ${customerIdx}`);

        this.$router.push("/");
      } else if (result && result.status === false) {
        // status가 0이면 로그인 거부
        alert("이메일 인증을 해주세요.");
      } else {
        // 그 외 경우는 로그인 실패 처리
        alert("로그인 실패");
        this.loginForm = {
          customerEmail: '',
          customerPwd: ''
        };
      }
    }
  }
};
</script>







<style scoped>
a{
    text-decoration-line: none;
}
*{
    font-family: 'GmarketSans';
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
    /* background-color: #f9f9f9; */
    padding: 20px;
    border-radius: 8px;
    /* width: 500px; */
    text-align: center;
  }

  .text-with-image {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 20px;
    margin-top: -10px;
    color: #494949;
    font-size: 13px;
  }

  .text-with-image img {
    width: 100px;
    height: auto;
    margin-right: 5px;
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
  .login-container input:first-of-type{
    margin-bottom: 20px;
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
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* width: 500px; */
    align-content: center;
    align-items: center;
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
    margin: 0 10px;
    padding: 10px;
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
    width: 15px;
    height: 15px;
    margin-right: 3px;
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
