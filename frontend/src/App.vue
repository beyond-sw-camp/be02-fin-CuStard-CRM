<template>
  <div>
    <HeaderComponent v-if="!isLoginPage && !isSignupPage" />
    <router-view></router-view>
    <FooterComponent v-if="!isLoginPage && !isSignupPage" />
  </div>
</template>


<script>
import HeaderComponent from "./components/HeaderComponent.vue"
import FooterComponent from "./components/FooterComponent.vue"

export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent,
  },
  data() {
    return {
      // 현재 페이지가 로그인 페이지인지 여부를 확인하는 변수를 추가합니다.
      isLoginPage: false,
      // 현재 페이지가 회원가입 페이지인지 여부를 확인하는 변수를 추가합니다.
      isSignupPage: false
    };
  },
  // 컴포넌트가 생성될 때 실행되는 created 훅에서 현재 페이지의 경로를 확인하여 각 페이지의 여부를 설정합니다.
  created() {
    this.checkIfLoginPage();
    this.checkIfSignupPage();
  },
  methods: {
    checkIfLoginPage() {
      // 현재 페이지의 경로를 확인하여 로그인 페이지인지 여부를 설정합니다.
      this.isLoginPage = this.$route.path === '/login';
    },
    checkIfSignupPage() {
      // 현재 페이지의 경로를 확인하여 회원가입 페이지인지 여부를 설정합니다.
      this.isSignupPage = this.$route.path === '/signup';
    }
  },
  // 라우트가 변경될 때마다 각 페이지의 여부를 다시 확인합니다.
  watch: {
    $route() {
      this.checkIfLoginPage();
      this.checkIfSignupPage();
    }
  }
}
</script>

<style>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/static/pretendard-dynamic-subset.min.css");
html, body{
  font-family: "Pretendard";
  font-weight: bold;
  font-style: normal;
}
</style>
