import { createRouter, createWebHistory } from 'vue-router'
import QnaListPage from '@/page/QnaListPage.vue'
import QnaListReadPage from '@/page/QnaListReadPage.vue'
import MainDashboardPage from '@/page/MainDashboard.vue'

const routes = [
    { path: '/admin/qna/list', component: QnaListPage },   
    { path: '/admin/qna/read/:idx', component: QnaListReadPage },
    { path: '/', component: MainDashboardPage },
]
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router
  