<template>
  <div class="email-verify"> 
        
        <div class="txt">
            메일함에서 이메일 인증 링크를 클릭해주세요.
            <br>
            인증 메일이 수신되지 않았다면 스팸함을 확인해주세요.
        </div>
  </div>
</template>

<script>
export default {
  name: 'VerifyPage',
}
</script>

<style>
  .email-verify{
    text-align: center; 
  }
  .txt{
    font-size: 30px; 
    text-align: center;
    margin: 100px 0; 
    padding: 200px;
  }
</style>