import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import DetailsComponent from  "@/components/DetailsComponent.vue"

// const requireAuth = () => (from, to, next) => {
//   const token = sessionStorage.getItem("aToken");
//   if (token != null) {
//     return next();
//   }
//   next("/login/member");
// };

const routes = [
  { path: "/", component: MainPage  },
  {
    path: "/productDetails",
    component: DetailsComponent,
  },
  { path: "/product", component: DetailsComponent },
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
