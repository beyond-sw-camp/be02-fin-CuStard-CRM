import { defineStore } from "pinia";
import axios from "axios";
import { toRaw } from "vue";

const backend = "http://localhost:8000";

export const useAdminStore = defineStore("admin", {
  state: () => ({ isLoading: false, isLoggedIn: false }),
  actions: {
    async login(adminLogin) {
      this.isLoading = true;
      try {
        let response = await axios.post(
            `${backend}/admin/login`,
            toRaw(adminLogin)
        );
        if (response.status === 200 && response.data.accessToken) {
          sessionStorage.setItem("atoken", response.data.accessToken);
          sessionStorage.setItem("adminIdx", response.data.idx);

          this.isLoggedIn = true;
          return {
            status: true,
            idx: response.data.idx,
            accessToken: response.data.accessToken,
            authority: response.data.authority // 예시로 추가한 권한 정보
          };
        }
      } catch (e) {
        console.error(e);
        return { status: false, message: "로그인 실패" };
      } finally {
        this.isLoading = false;
      }
    },
    // 기타 actions
  },
});
