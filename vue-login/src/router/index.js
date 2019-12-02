import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Success from '../components/Success'
import NotFound from '../components/NotFound'
Vue.use(Router)

const router = new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      redirect: '/'
    },
    {
      path: '/success',
      name:'Success',
      component:Success
    },
    {
      path: '*',
      name:'NotFound',
      component:NotFound
    }
  ]
})

//挂载导航拦截卫士
router.beforeEach((to, from, next)=>{

  if (to.path==='/') return next()
  const tokenStr = window.sessionStorage.getItem('hui-token');
  if (!tokenStr){
    return next('/')
  }else {
    next();
  }
})

export default router
