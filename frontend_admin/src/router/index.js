import { createRouter, createWebHistory } from 'vue-router'
import QnaListPage from '@/page/QnaListPage.vue'
import QnaListReadPage from '@/page/QnaListReadPage.vue'
import MainDashboardPage from '@/page/MainDashboard.vue'
import CustomerPage from '@/page/CustomerPage.vue'
import CustomerDetailsPage from '@/page/CustomerDetailsPage.vue'
import CouponPage from '@/page/CouponPage.vue'

const routes = [
    { path: '/', component: MainDashboardPage },

    { path: '/customerlist', component: CustomerPage },
    { path: '/customerdetail', component: CustomerDetailsPage },


    { path: '/coupon', component: CouponPage },

    { path: '/qnalist', component: QnaListPage },
    { path: '/qnaread:idx', component: QnaListReadPage },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
