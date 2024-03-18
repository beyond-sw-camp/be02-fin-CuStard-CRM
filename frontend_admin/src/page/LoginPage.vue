<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="row w-100 m-0">
        <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
          <div class="card col-lg-4 mx-auto">
            <div class="card-body px-5 py-5">
              <h3 class="card-title text-left mb-3">Login</h3>
              <form @submit.prevent="login">
                <div class="form-group">
                  <label>Username or email *</label>
                  <input type="text" class="form-control p_input" v-model="loginForm.adminEmail">
                </div>
                <div class="form-group">
                  <label>Password *</label>
                  <input type="password" class="form-control p_input" v-model="loginForm.adminPwd">
                </div>
                <div class="form-group d-flex align-items-center justify-content-between">
                  <div class="form-check">
                    <label class="form-check-label">
                      <input type="checkbox" class="form-check-input"> Remember me </label>
                  </div>
                  <a href="#" class="forgot-pass">Forgot password</a>
                </div>
                <div class="text-center">
                  <button type="button" class="btn btn-primary btn-block enter-btn" @click="login">Login</button>
                </div>
                <p class="sign-up">Don't have an Account?<a href="#"> Sign Up</a></p>
              </form>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- row ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
</template>

<script>
import {useAdminStore} from "@/stores/useAdminStore";

export default {
  data() {
    return {
      loginForm: {
        adminEmail: '',
        adminPwd: '',
      },
      isLoading: false // 로딩 상태를 추적하기 위한 변수
    };
  },
  methods: {
    async login() {
      const adminStore = useAdminStore();
      this.isLoading = true; // 로딩 상태 시작

      try {
        const result = await adminStore.login(this.loginForm);
        if (result && result.accessToken) {
          // 로그인 성공 처리
          localStorage.setItem('adminIdx', result.adminIdx);
          localStorage.setItem('accessToken', "Bearer " + result.accessToken);
          this.$router.push("/main");
        } else {
          // 결과에 accessToken이 없으면 로그인 실패로 간주
          throw new Error("로그인 실패");
        }
      } catch (error) {
        alert(error.message || "로그인에 실패했습니다.");
      } finally {
        this.isLoading = false; // 로딩 상태 종료
      }
    },
  }
};
</script>

<style>
/* Your styles here */
</style>
