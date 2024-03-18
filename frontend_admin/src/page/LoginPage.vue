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
                      <input type="checkbox" class="form-check-input"> Remember me
                    </label>
                  </div>
                  <a href="#" class="forgot-pass">Forgot password</a>
                </div>
                <div class="text-center">
                  <button type="submit" class="btn btn-primary btn-block enter-btn">Login</button>
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
import { useAdminStore } from "@/stores/useAdminStore";

export default {
  data() {
    return {
      loginForm: {
        adminEmail: '',
        adminPwd: '',
      }
    };
  },
  methods: {
    async login() {
      const adminStore = useAdminStore(); // 스토어 직접 사용
      const result = await adminStore.login(this.loginForm);
      console.log('ZZ')

      // 로그인 응답에서 authority 값을 확인
      if (result && result.authority === "Administrator") {
        console.log(`로그인 성공`);

        this.$router.push("/");
        // 로그인 성공 처리
      } else if (result && result.authority !== "Administrator") {
        // 권한이 Administrator가 아닌 경우
        alert("Administrator 권한이 필요합니다.");
      } else {
        // 로그인 실패 처리. result가 undefined이거나 message 속성이 없는 경우를 대비해 안전한 접근 방식 사용
        alert(result?.message || "로그인 실패");
        // 폼 초기화
      }
    },

  }
};
</script>

<style>

</style>
