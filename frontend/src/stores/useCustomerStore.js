import { defineStore } from "pinia";
import axios from "axios";
import { toRaw } from "vue";

// const backend = "http://192.168.0.53:80/api";
const backend = "http://localhost:8080";

export const useCustomerStore = defineStore("customer", {
  state: () => ({ isLoading: false, isLoggedIn: false }),
  actions: {
    async login(customerLogin) {
      this.isLoading = true;
      try {
        let response = await axios.post(
          backend + "/customer/login",
          toRaw(customerLogin)
        );
        if (response.data.code === 1000 && response.data.result.accessToken != null) {
          sessionStorage.setItem("atoken", response.data.result.accessToken);

          this.isLoggedIn = true;
          console.log(response.data.result.idx);
          sessionStorage.setItem("customerIdx", response.data.result.idx)

          return true;
        }
      } catch (e) {
        console.log(e);
        return false;
      } finally {
        this.isLoading = false;
      }
      return false;
    },
    async signup(customerSignup) {
      this.isLoading = true;
      try {
        let response = await axios.post(
          backend + "/customer/signup",
          toRaw(customerSignup)
        );
        this.isLoading = false;

        if (response.data.code === 1000 && response.data.isSuccess === true) {
          return true;
        }
      } catch (e) {
        return false;
      } finally {
        this.isLoading = false;
      }
      return false;

    },
  },
});
