import { createRouter, createWebHistory  } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import VerifyPage from '@/pages/VerifyPage.vue'
import LoginPage from "@/pages/LoginPage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import OrderCompletePage from "@/pages/OrderCompletePage.vue";
import DetailsComponent from  "@/components/DetailsComponent.vue";
import SearchResultPage from "@/pages/SearchResultPage.vue";

const routes = [
  // { path: "/", component: MainPage, beforeEnter: requireAuth() },
  { path: "/", component: MainPage  },
  { path: '/verify', component: VerifyPage },
  {path: "/product/:productIdx", component: DetailsComponent},
  { path: "/product", component: DetailsComponent },
  { path: "/order/complete/", name:'orderCompletePage', component: OrderCompletePage,},
  { path: "/search/:keyword",
    name: "SearchResult",
    component: SearchResultPage,
    props: true},
  { path: "/login", component: LoginPage},
  { path: "/Signup", component: SignupPage},
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
