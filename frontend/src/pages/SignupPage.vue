<template>
    <div class="login-container">
  <div class="content-container">
    <br>
    <div>
      <div class="signupfont">
        <h3>회원가입</h3>
    </div>
    </div>
    <br>
  </div>
  <br>
      
     <form id="signupForm" @submit.prevent >
   <div class="insertEmail">
    <h5>이메일</h5>
  </div>
    <input type="text" placeholder="이메일" id="이메일" v-model="memberSignup.email" required>
    <br>
    <div class="emailAuth">
    <button type="button" onclick="sendEmailVerification()">
      이메일 인증하기
    </button>   
    </div>

    <div class="insertpassword">
      <h5>비밀번호</h5>
    </div>
    <div class="insertpassword2">
      영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
    </div>
         <input type = "password" placeholder = "비밀번호 확인" id = "passwordcheck" v - model = "memberSignup.passwordConfirm" @blur = "checkPasswordsMatch" required > <div v-if="passwordMatchError" class="error">비밀번호가 일치하지 않습니다.</div>
    <br>
    
    <div class="insertcheck">
      <h5>비밀번호 확인</h5>
    </div>
    <input type="password" placeholder="비밀번호 확인" id="passwordcheck" required>
 
    <div class="consent-container">
      <label class="consent-label"><h5>약관동의</h5></label>
      <div class="consent-options">
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agreeAll" class="checkbox-input">
                  </div>
                  <span class="consent-text">
                    <h4>전체동의<span class="additional-consent"> &nbsp; 선택항목에 대한 동의 포함</span></h4>
                </span>
              </label>
          </div>
          <hr class="consent-divider">
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agree1" class="checkbox-input">
                  </div>
                  <span class="consent-text" required>만 14세 이상입니다 (필수)</span>
              </label>
          </div>
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agree2" class="checkbox-input">
                  </div>
                  <span class="consent-text" required>이용약관 (필수)</span>
                  <a target="_blank" href="/usepolicy" class="external-link"><span class="_chevron_right_18"></span></a>
              </label>
          </div>
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agree3" class="checkbox-input">
                  </div>
                  <span class="consent-text" required>개인정보수집 및 이용동의 (필수)</span>
                  <a target="_blank" href="/privacy?type=register" class="external-link"><span class="_chevron_right_18"></span></a>
              </label>
          </div>
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agreeMarketUsage" class="checkbox-input">
                  </div>
                  <span class="consent-text">개인정보 마케팅 활용 동의 (선택)</span>
                  <a target="_blank" href="/privacy?type=register_marketing" class="external-link"><span class="_chevron_right_18"></span></a>
              </label>
          </div>
          <div class="consent-option">
              <label class="checkbox-label">
                  <div class="checkbox-wrapper">
                      <div class="check-icon"><span class="_check_12"></span></div>
                      <input type="checkbox" value="false" field="[object Object]" name="agreePromotion" class="checkbox-input">
                  </div>
                  <span class="consent-text">이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신 (선택)
                    <div class="selection"></div></span>
              </label>
          </div>
      </div>
  </div>
    <br>
        <button type="button" @click="signup">
            회원 가입하기
        </button>
    </form>
    </div>
</template>

<script>

</script>



<style scoped>
*{
    font-family: 'GmarketSans';
  }
  
  body {
    background-color: #f9f9f9;
    margin: 390px;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: -50px;
    margin-bottom: 0px;
    width: 50%;
  }
  
  .login-container {
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
    width: 30%;
    text-align: center;
    margin-top: 20px;
  }
  
  .text-with-image {
    display: flex;
    align-items: center;
    margin-bottom: 0px;
    margin-top: -10px;
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
    width: 100px;
    height: auto;
    margin-right: 5px;
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
    background-color: #99154e;
  }
  
  .content-container {
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
    background-color: #fae100;
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
    width: 20px;
    height: 20px;
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
    margin-top: -20px;
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
    background-color: #ffbe0e;
    border-color: #ffbe0e;
    border: solid 1px;
    border-radius: 4px;
    cursor: pointer;
    transition: all 0.5s;
  }
  
  .emailAuth button:hover {
    background-color: #99154e;
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
  
  .insert-address, .insert-phone {
    text-align: left;
    margin-bottom: -15px;
    font-size: 15px;
    color: #494949;
  }
  
  .insert-address input, .insert-phone input {
    width: 100%;
    padding: 10px;
    margin-bottom: 0px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .insert-photo {
    text-align: left;
    margin-bottom: -15px;
    font-size: 15px;
    color: #494949;
  }
  
  .insert-business-number {
    text-align: left;
    margin-bottom: -15px;
    font-size: 15px;
    color: #494949;
  }
  
  .productImg {
    height: 300px;
    background-color: rgb(227, 228, 228);
    border-radius: 10px;
    margin-bottom: 30px;
    align-items: center;
    text-align: center;
    display: flex;
    justify-content: center;
  }
  
  .login-container {
    width: 80%;
    margin: 50px auto;
  }
  
  .content-container {
    text-align: center;
  }
  
  .insert-photo {
    margin-bottom: -13px;
  }
  
  .insert-photo h5 {
    margin: 0;
  }
  
  .productImg {
    margin-top: 20px;
  }
  
  .css-1rgmcy8 {
    border: 2px dashed #ccc;
    padding: 20px;
    text-align: center;
  }
  
  .fa-camera {
    font-size: 24px;
  }
  
  .css-nd2q8i {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin-top: 10px;
    cursor: pointer;
  }
</style>