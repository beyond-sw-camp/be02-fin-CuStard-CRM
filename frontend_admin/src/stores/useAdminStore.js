import { defineStore } from "pinia";
import axios from "axios";

let backend = "http://192.168.0.52:80/api";
// let backend = "http://localhost:8080";

export const useAdminStore = defineStore("admin", {
    state: () => ({
        isLoading: false,
        isLoggedIn: false,
    }),
    actions: {
        async login(adminLogin) {
            this.isLoading = true;
            try {
                let response = await axios.post(`${backend}/admin/login`, adminLogin);
                if (response.data.code === 1000 && response.data.result.accessToken) {
                    this.isLoggedIn = true;
                    return {
                        accessToken: response.data.result.accessToken,
                        adminIdx: response.data.result.idx
                    };
                } else {
                    throw new Error("로그인 정보가 올바르지 않습니다.");
                }
            } catch (e) {
                console.error(e);
                return { status: false, message: e.message || "서버 오류로 인한 로그인 실패" };
            } finally {
                this.isLoading = false;
            }
        },
        // 기타 actions
    },
});
