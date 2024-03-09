import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import DetailsComponent from  "@/components/DetailsComponent.vue";

// const requireAuth = () => (from, to, next) => {
//   const token = sessionStorage.getItem("aToken");
//   if (token != null) {
//     return next();
//   }
//   next("/login/member");
// };

const routes = [
  { path: "/", component: MainPage  },
  {path: "/productDetails", component: DetailsComponent},
  { path: "/product", component: DetailsComponent },
  { path: "/login", component: LoginPage},
  { path: "/Signup", component: SignupPage},
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
