<template>
  <div class="signup-content">
    <div class="signup-container">
      <form id="signupForm">
        <div class="insertEmail"><h5>이메일</h5></div>
        <input placeholder="이메일" type="email" id="customerEmail" v-model="customerSignup.customerEmail" maxlength="100">
        <br>
        <div class="insertpassword"><h5>비밀번호</h5></div>
        <div class="insertpassword2">영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</div>
        <input placeholder="비밀번호" type="password" id="customerPwd" v-model="customerSignup.customerPwd" maxlength="100">
        <br>
        <div class="consent-container">
          <label class="consent-label"><h5>약관동의</h5></label>
          <div class="consent-options">
            <div class="consent-option">
              <label class="checkbox-label">
                <div class="checkbox-wrapper">
                  <div class="check-icon"><span class="_check_12"></span></div>
                  <input type="checkbox" v-model="consent.agreeAll" class="checkbox-input">
                </div>
                <span class="consent-text">
                  <h4>전체동의<span class="additional-consent"> 선택항목에 대한 동의 포함</span></h4>
                </span>
              </label>
            </div>
            <hr class="consent-divider">
            <div class="consent-option" v-for="(value, name) in consent" :key="name">
              <label class="checkbox-label">
                <div class="checkbox-wrapper">
                  <div class="check-icon"><span class="_check_12"></span></div>
                  <input type="checkbox" v-model="consent[name]" class="checkbox-input">
                </div>
                <span class="consent-text">{{ getConsentLabel(name) }}</span>
              </label>
            </div>
          </div>
        </div>
        <br>
        <div>
          <input class="loginsubmit" type="submit" value="가입하기" @click.prevent="signup()">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { mapStores } from 'pinia'
import { useCustomerStore } from '@/stores/useCustomerStore';

export default {
  data() {
    return {
      customerSignup: {
        customerEmail: '',
        customerPwd: '',
      },
      consent: {
        agreeAll: false,
        agree1: false,
        agree2: false,
        agree3: false,
        agreeMarketUsage: false,
        agreePromotion: false
      }
    };
  },
  computed: {
    ...mapStores(useCustomerStore)
  },
  watch: {
    'consent.agreeAll'(newVal) {
      Object.keys(this.consent).forEach(key => {
        this.consent[key] = newVal;
      });
    }
  },
  methods: {
    async signup() {
      let result = await this.customerStore.signup(this.customerSignup)
      if (result === true) {
        window.location.href = "/customer/login";
      } else {
        alert("회원 가입 실패");
        this.resetSignupForm();
      }
    },
    resetSignupForm() {
      this.customerSignup = {
        customerEmail: '',
        customerPwd: '',
      };
      this.resetConsent();
    },
    resetConsent() {
      Object.keys(this.consent).forEach(key => {
        this.consent[key] = false;
      });
    },
    getConsentLabel(key) {
      const labels = {
        agree1: '만 14세 이상입니다 (필수)',
        agree2: '이용약관 (필수)',
        agree3: '개인정보수집 및 이용동의 (필수)',
        agreeMarketUsage: '개인정보 마케팅 활용 동의 (선택)',
        agreePromotion: '이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신 (선택)',
      };
      return labels[key] || '';
    }
  }
};
</script>




<style>
*{
  font-family: 'GmarketSans';
}

body {
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  margin-bottom: 50px;
  width: 100%;
}

.signup-content {
  justify-content: center;
  display: flex;
}

.signup-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  width: 40%;
  text-align: center;
  margin-top: 20px; /* 이미지와 로고에 겹치지 않도록 여백 추가 */
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
  margin-right: 5px;
  border-radius: 20%;
  padding-left: 40px;

}

.signup-container h2 {
  color: #333;
}

.signup-container input {
  width: 100%;
  padding: 10px;
  margin-bottom: 0px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.signup-container button {
  width: 100%;
  padding: 10px;
  background-color: #18cc3c;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.signup-container button:hover {
  background-color: #00ab03;
}

.signup-content-container {
  text-align: center;
  margin-top: 20px;
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
  background-color: #ffeb00;
}

.sns-buttons .naver {
  background-color: #00c63b;
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
  border: none;
  height: 1px;
  background-color: #c2c8cc;
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
  background-color: #00ab03;
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
.loginsubmit{
  transition: all 0.3s ease 0s;
  background: #ffbe0e;
  color: white;
}
.loginsubmit:hover{
  background: #99154e;
}

</style>
