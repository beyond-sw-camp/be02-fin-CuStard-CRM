import { defineStore } from "pinia";
import axios from "axios";
import { toRaw } from "vue";

const backend = "http://192.168.0.53:80/api";

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
        if (response.status === 200 && response.data.accessToken != null) {
          sessionStorage.setItem("atoken", response.data.accessToken);

          this.isLoggedIn = true;
          console.log(response.data.idx);
          sessionStorage.setItem("customerIdx", response.data.idx)
  
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

        if (response.status === 200 && response.data.isSuccess === true) {
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
