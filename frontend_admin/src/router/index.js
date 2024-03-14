import { createRouter, createWebHistory } from 'vue-router'
import QnaListPage from '@/page/QnaListPage.vue'
import QnaListReadPage from '@/page/QnaListReadPage.vue'


const routes = [
    { path: '/admin/qna/list', component: QnaListPage },   
    { path: '/admin/qna/read/:idx', component: QnaListReadPage },    
  ]
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router
  