import { createRouter, createWebHistory } from 'vue-router';
import BasketStudioPage from '../pages/BasketStudioPage.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'basket-studio',
      component: BasketStudioPage
    }
  ]
});

export default router;
