// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import CardDetail from '@/views/card/CardDetail.vue'
import HomePage from '@/views/HomePage.vue'
import CategoryPage from '@/views/CategoryPage.vue'
import FavoritePage from '@/views/FavoritePage.vue'
import LoginPage from '@/views/LoginPage.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
  },
  {
    path: '/movie/:id', // Dynamic route for card details
    name: 'CardDetail',
    component: CardDetail,
  },
  {
    path: '/favorite', // Dynamic route for card details
    name: 'Favorite',
    component: FavoritePage,
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  },
  { path: '/:category', component: CategoryPage, props: true }, // Dynamic category route
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
