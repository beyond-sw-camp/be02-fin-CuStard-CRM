import { createRouter, createWebHistory } from 'vue-router'
import QnaListPage from '@/page/QnaListPage.vue'
import QnaListReadPage from '@/page/QnaListReadPage.vue'
import MainDashboardPage from '@/page/MainDashboard.vue'
import CustomerPage from '@/page/CustomerPage.vue'
import CustomerDetailsPage from '@/page/CustomerDetailsPage.vue'
import CouponPage from '@/page/CouponPage.vue'
import LoginPage from "@/page/LoginPage.vue";


const routes = [
    { path: '/main', component: MainDashboardPage },

    { path: '/customerlist', component: CustomerPage },
    { path: '/customerdetail/:customerId', name: 'CustomerDetail', component: CustomerDetailsPage },


    { path: '/coupon', component: CouponPage },

    { path: '/qnalist', component: QnaListPage },
    { path: '/qnaread:idx', component: QnaListReadPage },

    { path: '/', component: LoginPage, meta: { hideNav: true }, name: 'login', },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})
function isLoggedIn() {
    return !!localStorage.getItem('accessToken');
}
// 전역 전에 가드 추가
router.beforeEach((to, from, next) => {
    if (to.path !== '/' && !isLoggedIn()) {
        // 로그인 페이지가 아니고, 사용자가 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        next('/');
    } else {
        // 그 외의 경우엔 정상적으로 라우트 이동 허용
        next();
    }
});

export default router
