import { createRouter, createWebHistory  } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import VerifyPage from '@/pages/VerifyPage.vue'
import LoginPage from "@/pages/LoginPage.vue";
import QnaListPage from "@/pages/QnaListPage.vue";
import QnaListReadPage from "@/pages/QnaListReadPage.vue";
import QnaRegisterPage from "@/pages/QnaRegisterPage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import DetailsComponent from  "@/components/DetailsComponent.vue";
import SearchResultPage from "@/pages/SearchResultPage.vue";
import OrderCompletePage from "@/pages/OrderCompletePage.vue";


const routes = [
  // { path: "/", component: MainPage, beforeEnter: requireAuth() },
  { path: "/", component: MainPage  },
  { path: '/verify', component: VerifyPage },
  {path: "/product/:productIdx", component: DetailsComponent},
  { path: "/product", component: DetailsComponent },
  {path: "/search", component: SignupPage},
  { path: "/order/complete/", name:'orderCompletePage', component: OrderCompletePage,},
  { path: "/search/:keyword",
    name: "SearchResult",
    component: SearchResultPage,
    props: true},
  { path: "/order/complete/",
    name:'orderCompletePage', component: OrderCompletePage,},
  { path: '/signup', component: SignupPage },
  { path: "/login", component: LoginPage},
  { path: "/qna/list", component: QnaListPage},
  { path: '/qna/read/:idx', component: QnaListReadPage},
  { path: "/qna/write", component: QnaRegisterPage},
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
